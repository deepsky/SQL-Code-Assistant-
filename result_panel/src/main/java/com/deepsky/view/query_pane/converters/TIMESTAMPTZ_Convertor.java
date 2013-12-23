/*
 * Copyright (c) 2009,2010 Serhiy Kulyk
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 *     1. Redistributions of source code must retain the above copyright
 *       notice, this list of conditions and the following disclaimer.
 *     2. Redistributions in binary form must reproduce the above copyright
 *       notice, this list of conditions and the following disclaimer in the
 *       documentation and/or other materials provided with the distribution.
 *
 * SQL CODE ASSISTANT PLUG-IN FOR INTELLIJ IDEA IS PROVIDED BY SERHIY KULYK
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL SERHIY KULYK BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.deepsky.view.query_pane.converters;

import com.deepsky.settings.SqlCodeAssistantSettings;
import com.deepsky.utils.StringUtils;
import com.deepsky.view.query_pane.ConversionException;
import com.deepsky.view.query_pane.ValueConvertor;
import com.deepsky.view.query_pane.util.DateTimeParser;
import oracle.sql.TIMESTAMP;
import oracle.sql.TIMESTAMPTZ;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TIMESTAMPTZ_Convertor implements ValueConvertor<TIMESTAMPTZ> {
    private SqlCodeAssistantSettings settings;
    private DateTimeParser parser;

    public TIMESTAMPTZ_Convertor(DateTimeParser parser, SqlCodeAssistantSettings settings) {
        this.settings = settings;
        this.parser = parser;
    }

    public long size(TIMESTAMPTZ value) throws SQLException {
        return valueToString(value).length();
    }

    public String valueToString(TIMESTAMPTZ value) throws SQLException {
        if (value == null) {
            return "";
        } else {
            Timestamp timestamp = extractDatetime(value);
            String result = dateTimeFormat();

            if (result != null) {
                result = new SimpleDateFormat(result).format(timestamp);
            }
            String tz = extractTZ(value);
            result = result != null && result.length() > 0 ? result + " " + tz : tz;

            return result;
        }
    }

    private String dateTimeFormat() {
        String result = "";
        String dateFormat = settings.getDateFormat();
        String timeFormat = settings.getTimeFormat();

        if (dateFormat != null) {
            result = dateFormat;
        }
        if (timeFormat != null) {
            result = result.length() > 0 ? result + " " + timeFormat : timeFormat;
        }
        return result;
    }

    //static Parser _parser = new Parser(TimeZone.getTimeZone("GMT"));

    public TIMESTAMPTZ stringToValue(String stringPresentation) throws ConversionException {
        // timestamp '2003-02-17 09:00:00 -8:00'
        // timestamp '2003-02-17 09:00:00 +13:45'
        // timestamp '2003-02-17 09:00:00 +1'
        if (stringPresentation != null && stringPresentation.length() > 0) {
            try {
                Calendar c = parser.parse(stringPresentation);
                if (c == null) {
                    return null;
                }

                String rest = c.getTimeZone().getID();
                byte[] tzRaw = parseTimeZone(rest);

                java.sql.Timestamp sqlDate = new java.sql.Timestamp(c.getTimeInMillis()); //date.getTime());
                TIMESTAMP temp = new TIMESTAMP(sqlDate);
                byte[] tsBytes = temp.getBytes();
                byte[] tstzBytes = new byte[13];
                System.arraycopy(tsBytes, 0, tstzBytes, 0, tsBytes.length); //11);
                tstzBytes[11] = tzRaw[0];
                tstzBytes[12] = tzRaw[1];
                return new TIMESTAMPTZ(tstzBytes);
            } catch (Throwable e) {
                throw new ConversionException();
            }
        }

        return null;
    }

    private String extractTZ(TIMESTAMPTZ value) {
        byte[] bytes = value.getBytes();
        String key = bytes[11] + ":" + bytes[12];
        Integer index;
        if ((index = key2index.get(key)) != null) {
            // time zone is named
            return keyValuePairs[index].key();
        } else {
            // time zone is timed
            return convertRaw2DTH_DTM(bytes[11], bytes[12]);
        }
    }

    public void saveValueTo(TIMESTAMPTZ value, @NotNull File file) throws IOException {
        try {
            StringUtils.string2file(valueToString(value), file);
        } catch (SQLException e) {
            throw new IOException("Could not save TIMESTAMPTZ in the file", e);
        }
    }


    private static final Pattern TZ_TIMED = Pattern.compile("([\\-0-9a-z]+) *(\\: *([0-9a-z]+))?.*");
    private static final Pattern TZ_TIMED_VERIFIER = Pattern.compile("([\\-0-9\\ ]+)(\\:([0-9\\ ]+))?.*");
    private static final Pattern GMT_TZ = Pattern.compile("gmt(\\+|-)([0-9]{1,2})(\\:[0-9]{1,})?");

    /**
     * Parser timezone string: GMT, Pacific/Majuro, +1, 1:00, -9, -0, 0, GMT+01:00
     *
     * @param _tz
     * @return
     * @throws ConversionException
     */
    public byte[] parseTimeZone(String _tz) throws ConversionException {
        String tz = _tz.trim();
        short tzShort = -1;
        if (tz.length() == 0) {
            // no timezone entered
            // todo use GMT timezone as default
            Integer idx = key2index.get("gmt");
            tzShort = keyValuePairs[idx + 1].value();
        } else {
            // try to parse timezone
            String restLow = tz.toLowerCase();
            // Do some adaptation for GMT time zone like: GMT+01:00
            Matcher gmt = GMT_TZ.matcher(restLow);
            if (gmt.find()) {
                String g1 = gmt.group(1);
                String g2 = gmt.group(2);
                String g3 = gmt.group(3);
                restLow = "gmt" + g1 + Integer.parseInt(g2);
            }
            for (int idx = 0; idx < keyValuePairs.length; idx += 2) {
                if (restLow.equalsIgnoreCase(keyValuePairs[idx].key())) {
                    tzShort = keyValuePairs[idx + 1].value();
                    break;
                }
/*
                if (restLow.startsWith(keyValuePairs[idx].key().toLowerCase())) {
                    tzShort = keyValuePairs[idx + 1].value();
                    break;
                }
*/
            }
            if (tzShort == -1) {
                // time zone is not named, try it out as timed
                Matcher m = TZ_TIMED.matcher(restLow);
                if (m.find()) {
                    String _1st = m.group(1);
                    String _2nd = m.group(2);
                    String _3d = m.group(3);
                    try {
                        int hours = Integer.parseInt(_1st.trim());
                        int minutes = _2nd != null ? Integer.parseInt(_3d.trim()) : 0;
                        // ORA-01874: time zone hour must be between -12 and 14
                        // ORA-01875: time zone minute must be between -59 and 59

                        // 08.60 < x < 34.60
                        int result = (hours * 60 + (hours < 0 ? -minutes : minutes));
                        // round up result
                        if (result < 0) {
                            result %= 12 * 60 + 60;
                        } else {
                            result %= 14 * 60;
                        }
                        hours = result / 60;
                        minutes = result %= 60;
                        byte _hours = (byte) (hours + 20);
                        byte _minutes = (byte) (minutes + 60);
                        return new byte[]{_hours, _minutes};
                    } catch (NumberFormatException e) {
                        // fall down
                    }
                }
                throw new ConversionException();
            }
        }
        byte[] tzRaw = new byte[2];
        ByteBuffer bbuffer = ByteBuffer.wrap(tzRaw);
        bbuffer.asShortBuffer().put(tzShort);
        return tzRaw;
    }

    public String convertRaw2DTH_DTM(short hi, short low) {
        int hours = hi - 20;
        int minutes = low - 60;
        return String.format("%1$02d:%2$02d", hours, minutes);
    }

/*
    public void saveValueTo(TIMESTAMPTZ value, File file) throws IOException {
        try {
            if (value == null) {
                StringUtils.string2file("", file);
            } else {
                String svalue = valueToString(value);
                StringUtils.string2file(svalue, file);
            }
        } catch (SQLException e) {
            throw new IOException(e.getMessage());
        }
    }
*/


    public static Timestamp extractDatetime(TIMESTAMPTZ value) throws SQLException {
        // extract(TIMEZONE_HOUR FROM TIMESTAMP '1999-01-15 8:00:00 -8:00')
        byte[] bytes = value.getBytes();
        byte[] _bytes = new byte[11];
        System.arraycopy(bytes, 0, _bytes, 0, 11);
        return TIMESTAMP.toTimestamp(_bytes);
    }

    private static KeyValuePair[] keyValuePairs = {
            new KeyValuePair("Africa/Addis_Ababa", 3),
            new KeyValuePair("-128:-68", -32580),
            new KeyValuePair("Africa/Bissau", 0),
            new KeyValuePair("-128:-48", -32560),
            new KeyValuePair("Africa/Ceuta", 1),
            new KeyValuePair("-127:68", -32444),
            new KeyValuePair("Africa/Harare", 2),
            new KeyValuePair("-127:64", -32448),
            new KeyValuePair("Africa/Kinshasa", 1),
            new KeyValuePair("-128:-100", -32612),
            new KeyValuePair("Africa/Maputo", 2),
            new KeyValuePair("-128:-4", -32516),
            new KeyValuePair("Africa/Windhoek", 2),
            new KeyValuePair("-127:0", -32512),
            new KeyValuePair("America/Argentina/Buenos_Aires", -3),
            new KeyValuePair("-118:-68", -30020),
            new KeyValuePair("America/Cayenne", -3),
            new KeyValuePair("-125:24", -31976),
            new KeyValuePair("America/Cordoba", -3),
            new KeyValuePair("-126:-60", -32060),
            new KeyValuePair("America/Danmarkshavn", 0),
            new KeyValuePair("-125:124", -31876),
            new KeyValuePair("America/Guadeloupe", -4),
            new KeyValuePair("-126:120", -32136),
            new KeyValuePair("America/Hermosillo", -7),
            new KeyValuePair("-126:60", -32196),
            new KeyValuePair("America/Indiana/Indianapolis", -5),
            new KeyValuePair("-103:-68", -26180),
            new KeyValuePair("America/Indiana/Knox", -5),
            new KeyValuePair("-127:-60", -32316),
            new KeyValuePair("America/Knox_IN", -5),
            new KeyValuePair("-119:-60", -30268),
            new KeyValuePair("America/La_Paz", -4),
            new KeyValuePair("-126:-40", -32040),
            new KeyValuePair("America/Manaus", -4),
            new KeyValuePair("-125:0", -32000),
            new KeyValuePair("America/Mendoza", -3),
            new KeyValuePair("-126:-48", -32048),
            new KeyValuePair("America/Nipigon", -5),
            new KeyValuePair("-127:-16", -32272),
            new KeyValuePair("America/Panama", -5),
            new KeyValuePair("-126:-104", -32104),
            new KeyValuePair("America/Swift_Current", -6),
            new KeyValuePair("-126:0", -32256),
            new KeyValuePair("Asia/Beirut", 2),
            new KeyValuePair("-124:84", -31660),
            new KeyValuePair("Asia/Macau", 8),
            new KeyValuePair("-116:0", -29696),
            new KeyValuePair("Asia/Saigon", 7),
            new KeyValuePair("-124:-76", -31564),
            new KeyValuePair("Asia/Tokyo", 9),
            new KeyValuePair("-124:44", -31700),
            new KeyValuePair("Atlantic/Faeroe", 0),
            new KeyValuePair("-115:52", -29388),
            new KeyValuePair("Atlantic/St_Helena", 0),
            new KeyValuePair("-123:80", -31408),
            new KeyValuePair("Australia/Melbourne", 11),
            new KeyValuePair("-123:124", -31364),
            new KeyValuePair("Australia/West", 8),
            new KeyValuePair("-115:104", -29336),
            new KeyValuePair("Chile/EasterIsland", -5),
            new KeyValuePair("-113:12", -28916),
            new KeyValuePair("Eire", 0),
            new KeyValuePair("-115:-52", -29236),
            new KeyValuePair("GMT", 0),
            new KeyValuePair("-128:4", -32764),
            new KeyValuePair("GMT+3", -3),
            new KeyValuePair("-128:72", -32696),
            new KeyValuePair("GMT+7", -7),
            new KeyValuePair("-128:88", -32680),
            new KeyValuePair("GMT-11", 11),
            new KeyValuePair("-128:20", -32748),
            new KeyValuePair("GMT-3", 3),
            new KeyValuePair("-128:52", -32716),
            new KeyValuePair("Europe/Andorra", 1),
            new KeyValuePair("-123:-44", -31276),
            new KeyValuePair("Europe/Bratislava", 1),
            new KeyValuePair("-115:-24", -29208),
            new KeyValuePair("Europe/Dublin", 0),
            new KeyValuePair("-123:-52", -31284),
            new KeyValuePair("Europe/Jersey", 0),
            new KeyValuePair("-99:-60", -25148),
            new KeyValuePair("Europe/Nicosia", 2),
            new KeyValuePair("-116:4", -29692),
            new KeyValuePair("Europe/Tirane", 1),
            new KeyValuePair("-123:-48", -31280),
            new KeyValuePair("Europe/Zaporozhye", 2),
            new KeyValuePair("-122:104", -31128),
            new KeyValuePair("GMT+0", 0),
            new KeyValuePair("-104:4", -26620),
            new KeyValuePair("Indian/Chagos", 6),
            new KeyValuePair("-122:-48", -31024),
            new KeyValuePair("Indian/Mauritius", 4),
            new KeyValuePair("-122:-20", -30996),
            new KeyValuePair("Mexico/General", -6),
            new KeyValuePair("-118:52", -30156),
            new KeyValuePair("MST7MDT", -7),
            new KeyValuePair("-125:96", -31904),
            new KeyValuePair("NZ", 13),
            new KeyValuePair("-113:92", -28836),
            new KeyValuePair("NZ-CHAT", 13),
            new KeyValuePair("-113:96", -28832),
            new KeyValuePair("Pacific/Easter", -5),
            new KeyValuePair("-121:12", -30964),
            new KeyValuePair("Pacific/Palau", 9),
            new KeyValuePair("-121:108", -30868),
            new KeyValuePair("Poland", 1),
            new KeyValuePair("-114:56", -29128),
            new KeyValuePair("ROC", 8),
            new KeyValuePair("-117:-4", -29700),
            new KeyValuePair("US/Alaska", -9),
            new KeyValuePair("-119:-88", -30296),
            new KeyValuePair("Africa/Abidjan", 0),
            new KeyValuePair("-128:-88", -32600),
            new KeyValuePair("Africa/Asmara", 3),
            new KeyValuePair("-128:-72", -32584),
            new KeyValuePair("Africa/Brazzaville", 1),
            new KeyValuePair("-128:-92", -32604),
            new KeyValuePair("Africa/Douala", 1),
            new KeyValuePair("-128:-112", -32624),
            new KeyValuePair("Africa/Lome", 0),
            new KeyValuePair("-127:48", -32464),
            new KeyValuePair("Africa/Mogadishu", 3),
            new KeyValuePair("-127:28", -32484),
            new KeyValuePair("Africa/Monrovia", 0),
            new KeyValuePair("-128:-36", -32548),
            new KeyValuePair("Africa/Niamey", 1),
            new KeyValuePair("-127:4", -32508),
            new KeyValuePair("Africa/Nouakchott", 0),
            new KeyValuePair("-128:-16", -32528),
            new KeyValuePair("Africa/Ouagadougou", 0),
            new KeyValuePair("-128:-120", -32632),
            new KeyValuePair("Africa/Timbuktu", 0),
            new KeyValuePair("-120:-24", -30488),
            new KeyValuePair("Africa/Tripoli", 2),
            new KeyValuePair("-128:-32", -32544),
            new KeyValuePair("America/Araguaina", -3),
            new KeyValuePair("-126:-24", -32024),
            new KeyValuePair("America/Belize", -6),
            new KeyValuePair("-126:88", -32168),
            new KeyValuePair("America/Coral_Harbour", -5),
            new KeyValuePair("-117:116", -29836),
            new KeyValuePair("America/Cuiaba", -4),
            new KeyValuePair("-126:-12", -32012),
            new KeyValuePair("America/Grenada", -4),
            new KeyValuePair("-126:116", -32140),
            new KeyValuePair("America/Indiana/Tell_City", -5),
            new KeyValuePair("-127:120", -32392),
            new KeyValuePair("America/Juneau", -9),
            new KeyValuePair("-127:-96", -32352),
            new KeyValuePair("America/Kentucky/Louisville", -5),
            new KeyValuePair("-119:-52", -30260),
            new KeyValuePair("America/Louisville", -5),
            new KeyValuePair("-127:-52", -32308),
            new KeyValuePair("America/Montevideo", -3),
            new KeyValuePair("-125:48", -31952),
            new KeyValuePair("America/North_Dakota/New_Salem", -7),
            new KeyValuePair("-127:100", -32412),
            new KeyValuePair("America/Paramaribo", -3),
            new KeyValuePair("-125:40", -31960),
            new KeyValuePair("America/Rainy_River", -6),
            new KeyValuePair("-127:-12", -32268),
            new KeyValuePair("America/Resolute", -6),
            new KeyValuePair("-125:108", -31892),
            new KeyValuePair("America/Sao_Paulo", -3),
            new KeyValuePair("-126:-16", -32016),
            new KeyValuePair("America/Tortola", -4),
            new KeyValuePair("-126:-76", -32076),
            new KeyValuePair("America/Vancouver", -8),
            new KeyValuePair("-126:8", -32248),
            new KeyValuePair("Asia/Bahrain", 3),
            new KeyValuePair("-125:-52", -31796),
            new KeyValuePair("Asia/Brunei", 8),
            new KeyValuePair("-125:-40", -31784),
            new KeyValuePair("Asia/Dili", 9),
            new KeyValuePair("-124:12", -31732),
            new KeyValuePair("Asia/Hovd", 7),
            new KeyValuePair("-124:96", -31648),
            new KeyValuePair("Asia/Karachi", 5),
            new KeyValuePair("-124:112", -31632),
            new KeyValuePair("Asia/Kolkata", 5),
            new KeyValuePair("-116:16", -29680),
            new KeyValuePair("Asia/Riyadh", 3),
            new KeyValuePair("-124:-128", -31616),
            new KeyValuePair("Asia/Tehran", 3),
            new KeyValuePair("-124:32", -31712),
            new KeyValuePair("Asia/Ulaanbaatar", 8),
            new KeyValuePair("-124:100", -31644),
            new KeyValuePair("Atlantic/Stanley", -3),
            new KeyValuePair("-123:44", -31444),
            new KeyValuePair("Australia/Broken_Hill", 10),
            new KeyValuePair("-123:-124", -31356),
            new KeyValuePair("Chile/Continental", -3),
            new KeyValuePair("-117:8", -29944),
            new KeyValuePair("GMT+10", -10),
            new KeyValuePair("-128:100", -32668),
            new KeyValuePair("GMT+9", -9),
            new KeyValuePair("-128:96", -32672),
            new KeyValuePair("GMT-2", 2),
            new KeyValuePair("-128:56", -32712),
            new KeyValuePair("Europe/Athens", 2),
            new KeyValuePair("-122:4", -31228),
            new KeyValuePair("Europe/Bucharest", 2),
            new KeyValuePair("-122:64", -31168),
            new KeyValuePair("Europe/Guernsey", 0),
            new KeyValuePair("-91:-60", -23100),
            new KeyValuePair("Europe/Sofia", 2),
            new KeyValuePair("-123:-28", -31260),
            new KeyValuePair("Europe/Vienna", 1),
            new KeyValuePair("-123:-40", -31272),
            new KeyValuePair("Europe/Zagreb", 1),
            new KeyValuePair("-90:112", -22928),
            new KeyValuePair("GMT-0", 0),
            new KeyValuePair("-88:4", -22524),
            new KeyValuePair("Indian/Comoro", 3),
            new KeyValuePair("-122:-28", -31004),
            new KeyValuePair("Jamaica", -5),
            new KeyValuePair("-118:-120", -30072),
            new KeyValuePair("MST", -7),
            new KeyValuePair("-125:80", -31920),
            new KeyValuePair("Pacific/Chatham", 13),
            new KeyValuePair("-121:96", -30880),
            new KeyValuePair("Pacific/Noumea", 11),
            new KeyValuePair("-121:88", -30888),
            new KeyValuePair("US/Aleutian", -10),
            new KeyValuePair("-111:-80", -28240),
            new KeyValuePair("US/Arizona", -7),
            new KeyValuePair("-119:-76", -30284),
            new KeyValuePair("US/Pacific-New", -8),
            new KeyValuePair("-103:-100", -26212),
            new KeyValuePair("Africa/Dar_es_Salaam", 3),
            new KeyValuePair("-127:44", -32468),
            new KeyValuePair("Africa/Kigali", 2),
            new KeyValuePair("-127:12", -32500),
            new KeyValuePair("Africa/Porto-Novo", 1),
            new KeyValuePair("-128:-128", -32640),
            new KeyValuePair("America/Argentina/Catamarca", -3),
            new KeyValuePair("-118:-52", -30004),
            new KeyValuePair("America/Argentina/ComodRivadavia", -3),
            new KeyValuePair("-110:-52", -27956),
            new KeyValuePair("America/Bahia", -3),
            new KeyValuePair("-127:104", -32408),
            new KeyValuePair("America/Belem", -3),
            new KeyValuePair("-126:-32", -32032),
            new KeyValuePair("America/Boise", -7),
            new KeyValuePair("-127:-72", -32328),
            new KeyValuePair("America/Denver", -7),
            new KeyValuePair("-127:-104", -32360),
            new KeyValuePair("America/Eirunepe", -5),
            new KeyValuePair("-125:-124", -31868),
            new KeyValuePair("America/Indiana/Vevay", -5),
            new KeyValuePair("-127:-56", -32312),
            new KeyValuePair("America/Indiana/Vincennes", -5),
            new KeyValuePair("-125:68", -31932),
            new KeyValuePair("America/Kentucky/Monticello", -5),
            new KeyValuePair("-127:108", -32404),
            new KeyValuePair("America/Maceio", -3),
            new KeyValuePair("-126:-20", -32020),
            new KeyValuePair("America/Mexico_City", -6),
            new KeyValuePair("-126:52", -32204),
            new KeyValuePair("America/Moncton", -4),
            new KeyValuePair("-127:112", -32400),
            new KeyValuePair("America/Monterrey", -6),
            new KeyValuePair("-125:-116", -31860),
            new KeyValuePair("America/Porto_Acre", -5),
            new KeyValuePair("-125:4", -31996),
            new KeyValuePair("America/Yellowknife", -7),
            new KeyValuePair("-126:32", -32224),
            new KeyValuePair("Antarctica/Palmer", -3),
            new KeyValuePair("-125:-84", -31828),
            new KeyValuePair("Asia/Aqtobe", 5),
            new KeyValuePair("-124:56", -31688),
            new KeyValuePair("Asia/Dubai", 4),
            new KeyValuePair("-124:-88", -31576),
            new KeyValuePair("Asia/Kashgar", 8),
            new KeyValuePair("-125:-12", -31756),
            new KeyValuePair("Asia/Macao", 8),
            new KeyValuePair("-124:0", -31744),
            new KeyValuePair("Asia/Manila", 8),
            new KeyValuePair("-124:120", -31624),
            new KeyValuePair("Asia/Nicosia", 2),
            new KeyValuePair("-124:4", -31740),
            new KeyValuePair("Asia/Oral", 4),
            new KeyValuePair("-124:-20", -31508),
            new KeyValuePair("Asia/Qyzylorda", 6),
            new KeyValuePair("-124:-24", -31512),
            new KeyValuePair("Asia/Sakhalin", 10),
            new KeyValuePair("-124:-12", -31500),
            new KeyValuePair("Atlantic/Bermuda", -4),
            new KeyValuePair("-123:40", -31448),
            new KeyValuePair("Australia/Canberra", 11),
            new KeyValuePair("-107:-128", -27264),
            new KeyValuePair("Australia/NSW", 11),
            new KeyValuePair("-99:-128", -25216),
            new KeyValuePair("Australia/South", 10),
            new KeyValuePair("-115:116", -29324),
            new KeyValuePair("Australia/Tasmania", 11),
            new KeyValuePair("-115:120", -29320),
            new KeyValuePair("Brazil/DeNoronha", -2),
            new KeyValuePair("-118:-36", -29988),
            new KeyValuePair("Brazil/East", -3),
            new KeyValuePair("-118:-16", -29968),
            new KeyValuePair("Canada/Yukon", -8),
            new KeyValuePair("-118:40", -30168),
            new KeyValuePair("EST5EDT", -5),
            new KeyValuePair("-125:88", -31912),
            new KeyValuePair("GMT+8", -8),
            new KeyValuePair("-128:92", -32676),
            new KeyValuePair("GMT-10", 10),
            new KeyValuePair("-128:24", -32744),
            new KeyValuePair("GMT-5", 5),
            new KeyValuePair("-128:44", -32724),
            new KeyValuePair("Etc/Greenwich", 0),
            new KeyValuePair("-64:4", -16380),
            new KeyValuePair("Europe/Brussels", 1),
            new KeyValuePair("-123:-32", -31264),
            new KeyValuePair("Europe/Malta", 1),
            new KeyValuePair("-122:32", -31200),
            new KeyValuePair("Europe/Podgorica", 1),
            new KeyValuePair("-82:112", -20880),
            new KeyValuePair("Europe/Prague", 1),
            new KeyValuePair("-123:-24", -31256),
            new KeyValuePair("Europe/Samara", 4),
            new KeyValuePair("-122:76", -31156),
            new KeyValuePair("Europe/San_Marino", 1),
            new KeyValuePair("-106:12", -27124),
            new KeyValuePair("Europe/Tiraspol", 2),
            new KeyValuePair("-114:36", -29148),
            new KeyValuePair("Iceland", 0),
            new KeyValuePair("-115:56", -29384),
            new KeyValuePair("Indian/Christmas", 7),
            new KeyValuePair("-122:-36", -31012),
            new KeyValuePair("Mexico/BajaSur", -7),
            new KeyValuePair("-118:64", -30144),
            new KeyValuePair("Pacific/Enderbury", 13),
            new KeyValuePair("-121:48", -30928),
            new KeyValuePair("Pacific/Fakaofo", -10),
            new KeyValuePair("-121:-120", -30840),
            new KeyValuePair("Pacific/Majuro", 12),
            new KeyValuePair("-121:60", -30916),
            new KeyValuePair("Pacific/Ponape", 11),
            new KeyValuePair("-121:76", -30900),
            new KeyValuePair("Pacific/Tahiti", -10),
            new KeyValuePair("-121:36", -30940),
            new KeyValuePair("Pacific/Tongatapu", 13),
            new KeyValuePair("-121:-116", -30836),
            new KeyValuePair("Pacific/Wake", 12),
            new KeyValuePair("-121:-100", -30820),
            new KeyValuePair("PRC", 8),
            new KeyValuePair("-117:-24", -29720),
            new KeyValuePair("Africa/Accra", 0),
            new KeyValuePair("-128:-56", -32568),
            new KeyValuePair("Africa/Bamako", 0),
            new KeyValuePair("-128:-24", -32536),
            new KeyValuePair("Africa/Conakry", 0),
            new KeyValuePair("-128:-52", -32564),
            new KeyValuePair("Africa/Johannesburg", 2),
            new KeyValuePair("-127:32", -32480),
            new KeyValuePair("Africa/Libreville", 1),
            new KeyValuePair("-128:-64", -32576),
            new KeyValuePair("Africa/Lusaka", 2),
            new KeyValuePair("-127:60", -32452),
            new KeyValuePair("Africa/Sao_Tome", 0),
            new KeyValuePair("-127:16", -32496),
            new KeyValuePair("America/Argentina/Cordoba", -3),
            new KeyValuePair("-118:-60", -30012),
            new KeyValuePair("America/Argentina/Salta", -3),
            new KeyValuePair("-125:-76", -31820),
            new KeyValuePair("America/Argentina/San_Luis", -3),
            new KeyValuePair("-127:-124", -32380),
            new KeyValuePair("America/Atka", -10),
            new KeyValuePair("-119:-80", -30288),
            new KeyValuePair("America/Barbados", -4),
            new KeyValuePair("-126:84", -32172),
            new KeyValuePair("America/Bogota", -5),
            new KeyValuePair("-125:12", -31988),
            new KeyValuePair("America/Buenos_Aires", -3),
            new KeyValuePair("-126:-68", -32068),
            new KeyValuePair("America/Costa_Rica", -6),
            new KeyValuePair("-126:96", -32160),
            new KeyValuePair("America/Guatemala", -6),
            new KeyValuePair("-126:124", -32132),
            new KeyValuePair("America/Indiana/Winamac", -5),
            new KeyValuePair("-125:104", -31896),
            new KeyValuePair("America/Marigot", -4),
            new KeyValuePair("-118:120", -30088),
            new KeyValuePair("America/Montserrat", -4),
            new KeyValuePair("-126:-112", -32112),
            new KeyValuePair("America/Phoenix", -7),
            new KeyValuePair("-127:-76", -32332),
            new KeyValuePair("America/Porto_Velho", -4),
            new KeyValuePair("-126:-8", -32008),
            new KeyValuePair("America/Rosario", -3),
            new KeyValuePair("-110:-60", -27964),
            new KeyValuePair("America/Santiago", -3),
            new KeyValuePair("-125:8", -31992),
            new KeyValuePair("America/Scoresbysund", -1),
            new KeyValuePair("-125:56", -31944),
            new KeyValuePair("America/St_Vincent", -4),
            new KeyValuePair("-126:-84", -32084),
            new KeyValuePair("America/Thule", -4),
            new KeyValuePair("-125:64", -31936),
            new KeyValuePair("America/Toronto", -5),
            new KeyValuePair("-125:112", -31888),
            new KeyValuePair("Antarctica/DumontDUrville", 10),
            new KeyValuePair("-125:-92", -31836),
            new KeyValuePair("Asia/Aden", 3),
            new KeyValuePair("-124:-72", -31560),
            new KeyValuePair("Asia/Amman", 2),
            new KeyValuePair("-124:48", -31696),
            new KeyValuePair("Asia/Ashkhabad", 5),
            new KeyValuePair("-116:-92", -29532),
            new KeyValuePair("Asia/Bishkek", 5),
            new KeyValuePair("-124:64", -31680),
            new KeyValuePair("Asia/Choibalsan", 9),
            new KeyValuePair("-124:-16", -31504),
            new KeyValuePair("Asia/Chungking", 8),
            new KeyValuePair("-125:-20", -31764),
            new KeyValuePair("Asia/Colombo", 6),
            new KeyValuePair("-124:-108", -31596),
            new KeyValuePair("Asia/Dhaka", 6),
            new KeyValuePair("-117:-48", -29744),
            new KeyValuePair("Asia/Dushanbe", 5),
            new KeyValuePair("-124:-100", -31588),
            new KeyValuePair("Asia/Gaza", 2),
            new KeyValuePair("-124:116", -31628),
            new KeyValuePair("Asia/Irkutsk", 8),
            new KeyValuePair("-124:-52", -31540),
            new KeyValuePair("Asia/Jerusalem", 2),
            new KeyValuePair("-124:40", -31704),
            new KeyValuePair("Asia/Novosibirsk", 6),
            new KeyValuePair("-124:-60", -31548),
            new KeyValuePair("Asia/Omsk", 6),
            new KeyValuePair("-124:-64", -31552),
            new KeyValuePair("Asia/Rangoon", 6),
            new KeyValuePair("-125:-36", -31780),
            new KeyValuePair("Asia/Tel_Aviv", 2),
            new KeyValuePair("-116:40", -29656),
            new KeyValuePair("Asia/Thimbu", 6),
            new KeyValuePair("-117:-44", -29740),
            new KeyValuePair("Asia/Vientiane", 7),
            new KeyValuePair("-124:80", -31664),
            new KeyValuePair("Asia/Vladivostok", 10),
            new KeyValuePair("-124:-44", -31532),
            new KeyValuePair("Atlantic/Canary", 0),
            new KeyValuePair("-123:72", -31416),
            new KeyValuePair("Australia/Currie", 11),
            new KeyValuePair("-123:-116", -31348),
            new KeyValuePair("Australia/Hobart", 11),
            new KeyValuePair("-123:120", -31368),
            new KeyValuePair("Australia/Lord_Howe", 11),
            new KeyValuePair("-123:-120", -31352),
            new KeyValuePair("Australia/Perth", 8),
            new KeyValuePair("-123:104", -31384),
            new KeyValuePair("Australia/Sydney", 11),
            new KeyValuePair("-123:-128", -31360),
            new KeyValuePair("Canada/Central", -6),
            new KeyValuePair("-119:-8", -30216),
            new KeyValuePair("GMT+0", 0),
            new KeyValuePair("-112:4", -28668),
            new KeyValuePair("GMT+5", -5),
            new KeyValuePair("-128:80", -32688),
            new KeyValuePair("GMT-8", 8),
            new KeyValuePair("-128:32", -32736),
            new KeyValuePair("GMT-9", 9),
            new KeyValuePair("-128:28", -32740),
            new KeyValuePair("Europe/Belgrade", 1),
            new KeyValuePair("-122:112", -31120),
            new KeyValuePair("Europe/Berlin", 1),
            new KeyValuePair("-123:-4", -31236),
            new KeyValuePair("Europe/Budapest", 1),
            new KeyValuePair("-122:8", -31224),
            new KeyValuePair("Europe/Gibraltar", 1),
            new KeyValuePair("-122:0", -31232),
            new KeyValuePair("Europe/Isle_of_Man", 0),
            new KeyValuePair("-83:-60", -21052),
            new KeyValuePair("Europe/Kaliningrad", 2),
            new KeyValuePair("-122:68", -31164),
            new KeyValuePair("Europe/Ljubljana", 1),
            new KeyValuePair("-114:112", -29072),
            new KeyValuePair("Europe/Luxembourg", 1),
            new KeyValuePair("-122:28", -31204),
            new KeyValuePair("Europe/Paris", 1),
            new KeyValuePair("-123:-8", -31240),
            new KeyValuePair("Europe/Tallinn", 2),
            new KeyValuePair("-123:-16", -31248),
            new KeyValuePair("Europe/Uzhgorod", 2),
            new KeyValuePair("-122:100", -31132),
            new KeyValuePair("Europe/Volgograd", 3),
            new KeyValuePair("-122:116", -31116),
            new KeyValuePair("GMT", 0),
            new KeyValuePair("-120:4", -30716),
            new KeyValuePair("HST", -10),
            new KeyValuePair("-125:84", -31916),
            new KeyValuePair("Indian/Cocos", 6),
            new KeyValuePair("-122:-32", -31008),
            new KeyValuePair("MET", 1),
            new KeyValuePair("-123:-68", -31300),
            new KeyValuePair("Mexico/BajaNorte", -8),
            new KeyValuePair("-110:68", -28092),
            new KeyValuePair("Pacific/Apia", -11),
            new KeyValuePair("-121:124", -30852),
            new KeyValuePair("Pacific/Efate", 11),
            new KeyValuePair("-121:-96", -30816),
            new KeyValuePair("Pacific/Fiji", 12),
            new KeyValuePair("-121:24", -30952),
            new KeyValuePair("Pacific/Guadalcanal", 11),
            new KeyValuePair("-121:-124", -30844),
            new KeyValuePair("Pacific/Johnston", -10),
            new KeyValuePair("-121:-108", -30828),
            new KeyValuePair("Pacific/Pitcairn", -8),
            new KeyValuePair("-121:116", -30860),
            new KeyValuePair("Pacific/Port_Moresby", 10),
            new KeyValuePair("-121:112", -30864),
            new KeyValuePair("Pacific/Samoa", -11),
            new KeyValuePair("-105:120", -26760),
            new KeyValuePair("Pacific/Truk", 10),
            new KeyValuePair("-121:72", -30904),
            new KeyValuePair("Pacific/Yap", 10),
            new KeyValuePair("-113:72", -28856),
            new KeyValuePair("US/Eastern", -5),
            new KeyValuePair("-119:-112", -30320),
            new KeyValuePair("US/Michigan", -5),
            new KeyValuePair("-119:-48", -30256),
            new KeyValuePair("Africa/Bangui", 1),
            new KeyValuePair("-128:-108", -32620),
            new KeyValuePair("Africa/Blantyre", 2),
            new KeyValuePair("-128:-28", -32540),
            new KeyValuePair("Africa/Cairo", 2),
            new KeyValuePair("-128:-80", -32592),
            new KeyValuePair("Africa/Khartoum", 3),
            new KeyValuePair("-127:36", -32476),
            new KeyValuePair("Africa/Lagos", 1),
            new KeyValuePair("-127:8", -32504),
            new KeyValuePair("Africa/Malabo", 1),
            new KeyValuePair("-128:-76", -32588),
            new KeyValuePair("Africa/Maseru", 2),
            new KeyValuePair("-128:-40", -32552),
            new KeyValuePair("Africa/Nairobi", 3),
            new KeyValuePair("-128:-44", -32556),
            new KeyValuePair("Africa/Tunis", 1),
            new KeyValuePair("-127:52", -32460),
            new KeyValuePair("America/Argentina/Tucuman", -3),
            new KeyValuePair("-125:-112", -31856),
            new KeyValuePair("America/Blanc-Sablon", -4),
            new KeyValuePair("-125:-128", -31872),
            new KeyValuePair("America/Cancun", -6),
            new KeyValuePair("-126:48", -32208),
            new KeyValuePair("America/Dominica", -4),
            new KeyValuePair("-126:104", -32152),
            new KeyValuePair("America/Havana", -5),
            new KeyValuePair("-126:100", -32156),
            new KeyValuePair("America/Indiana/Marengo", -5),
            new KeyValuePair("-127:-64", -32320),
            new KeyValuePair("America/Los_Angeles", -8),
            new KeyValuePair("-127:-100", -32356),
            new KeyValuePair("America/Managua", -6),
            new KeyValuePair("-126:-108", -32108),
            new KeyValuePair("America/Martinique", -4),
            new KeyValuePair("-126:-116", -32116),
            new KeyValuePair("America/Mazatlan", -7),
            new KeyValuePair("-126:64", -32192),
            new KeyValuePair("America/Menominee", -6),
            new KeyValuePair("-127:-44", -32300),
            new KeyValuePair("America/Miquelon", -3),
            new KeyValuePair("-126:-88", -32088),
            new KeyValuePair("America/Nome", -9),
            new KeyValuePair("-127:-84", -32340),
            new KeyValuePair("America/Rankin_Inlet", -6),
            new KeyValuePair("-126:24", -32232),
            new KeyValuePair("America/Santo_Domingo", -4),
            new KeyValuePair("-126:108", -32148),
            new KeyValuePair("America/Shiprock", -7),
            new KeyValuePair("-103:-104", -26216),
            new KeyValuePair("America/St_Thomas", -4),
            new KeyValuePair("-126:-72", -32072),
            new KeyValuePair("America/Virgin", -4),
            new KeyValuePair("-118:-72", -30024),
            new KeyValuePair("America/Whitehorse", -8),
            new KeyValuePair("-126:40", -32216),
            new KeyValuePair("America/Winnipeg", -6),
            new KeyValuePair("-127:-8", -32264),
            new KeyValuePair("America/Yakutat", -9),
            new KeyValuePair("-127:-92", -32348),
            new KeyValuePair("Antarctica/South_Pole", 13),
            new KeyValuePair("-117:-80", -29776),
            new KeyValuePair("Arctic/Longyearbyen", 1),
            new KeyValuePair("-114:52", -29132),
            new KeyValuePair("Asia/Baghdad", 3),
            new KeyValuePair("-124:36", -31708),
            new KeyValuePair("Asia/Baku", 4),
            new KeyValuePair("-125:-56", -31800),
            new KeyValuePair("Asia/Bangkok", 7),
            new KeyValuePair("-124:-96", -31584),
            new KeyValuePair("Asia/Calcutta", 5),
            new KeyValuePair("-124:16", -31728),
            new KeyValuePair("Asia/Damascus", 2),
            new KeyValuePair("-124:-104", -31592),
            new KeyValuePair("Asia/Harbin", 8),
            new KeyValuePair("-125:-28", -31772),
            new KeyValuePair("Asia/Jakarta", 7),
            new KeyValuePair("-124:20", -31724),
            new KeyValuePair("Asia/Kabul", 4),
            new KeyValuePair("-125:-64", -31808),
            new KeyValuePair("Asia/Singapore", 8),
            new KeyValuePair("-124:-112", -31600),
            new KeyValuePair("Asia/Urumqi", 8),
            new KeyValuePair("-125:-16", -31760),
            new KeyValuePair("Atlantic/Azores", -1),
            new KeyValuePair("-123:64", -31424),
            new KeyValuePair("Australia/Adelaide", 10),
            new KeyValuePair("-123:116", -31372),
            new KeyValuePair("Australia/LHI", 11),
            new KeyValuePair("-115:-120", -29304),
            new KeyValuePair("Brazil/West", -4),
            new KeyValuePair("-117:0", -29952),
            new KeyValuePair("Canada/Pacific", -8),
            new KeyValuePair("-118:8", -30200),
            new KeyValuePair("CST6CDT", -6),
            new KeyValuePair("-125:92", -31908),
            new KeyValuePair("Cuba", -5),
            new KeyValuePair("-118:100", -30108),
            new KeyValuePair("Egypt", 2),
            new KeyValuePair("-120:-80", -30544),
            new KeyValuePair("GMT+2", -2),
            new KeyValuePair("-128:68", -32700),
            new KeyValuePair("GMT+4", -4),
            new KeyValuePair("-128:76", -32692),
            new KeyValuePair("GMT0", 0),
            new KeyValuePair("-80:4", -20476),
            new KeyValuePair("GMT-14", 14),
            new KeyValuePair("-128:8", -32760),
            new KeyValuePair("Europe/Amsterdam", 1),
            new KeyValuePair("-122:48", -31184),
            new KeyValuePair("Europe/Helsinki", 2),
            new KeyValuePair("-123:-12", -31244),
            new KeyValuePair("Europe/Istanbul", 2),
            new KeyValuePair("-122:92", -31140),
            new KeyValuePair("Europe/Kiev", 2),
            new KeyValuePair("-122:96", -31136),
            new KeyValuePair("Europe/Lisbon", 0),
            new KeyValuePair("-122:60", -31172),
            new KeyValuePair("Europe/Mariehamn", 2),
            new KeyValuePair("-115:-12", -29196),
            new KeyValuePair("Europe/Minsk", 2),
            new KeyValuePair("-123:-36", -31268),
            new KeyValuePair("GB-Eire", 0),
            new KeyValuePair("-107:-60", -27196),
            new KeyValuePair("Greenwich", 0),
            new KeyValuePair("-56:4", -14332),
            new KeyValuePair("Indian/Antananarivo", 3),
            new KeyValuePair("-122:-40", -31016),
            new KeyValuePair("Indian/Mayotte", 3),
            new KeyValuePair("-122:-16", -30992),
            new KeyValuePair("Kwajalein", 12),
            new KeyValuePair("-113:64", -28864),
            new KeyValuePair("Pacific/Auckland", 13),
            new KeyValuePair("-121:92", -30884),
            new KeyValuePair("Pacific/Galapagos", -6),
            new KeyValuePair("-121:16", -30960),
            new KeyValuePair("Pacific/Honolulu", -10),
            new KeyValuePair("-121:8", -30968),
            new KeyValuePair("Pacific/Nauru", 12),
            new KeyValuePair("-121:84", -30892),
            new KeyValuePair("Pacific/Wallis", 12),
            new KeyValuePair("-121:-92", -30812),
            new KeyValuePair("US/Central", -6),
            new KeyValuePair("-119:-108", -30316),
            new KeyValuePair("US/Mountain", -7),
            new KeyValuePair("-111:-104", -28264),
            new KeyValuePair("UTC", 0),
            new KeyValuePair("-48:4", -12284),
            new KeyValuePair("Africa/Gaborone", 2),
            new KeyValuePair("-128:-124", -32636),
            new KeyValuePair("America/Asuncion", -3),
            new KeyValuePair("-125:32", -31968),
            new KeyValuePair("America/Campo_Grande", -4),
            new KeyValuePair("-125:120", -31880),
            new KeyValuePair("America/Chicago", -6),
            new KeyValuePair("-127:-108", -32364),
            new KeyValuePair("America/Detroit", -5),
            new KeyValuePair("-127:-48", -32304),
            new KeyValuePair("America/Edmonton", -7),
            new KeyValuePair("-126:4", -32252),
            new KeyValuePair("America/Ensenada", -8),
            new KeyValuePair("-118:68", -30140),
            new KeyValuePair("America/Goose_Bay", -4),
            new KeyValuePair("-127:-36", -32292),
            new KeyValuePair("America/Halifax", -4),
            new KeyValuePair("-127:-32", -32288),
            new KeyValuePair("America/Indiana/Petersburg", -5),
            new KeyValuePair("-125:72", -31928),
            new KeyValuePair("America/Indianapolis", -5),
            new KeyValuePair("-127:-68", -32324),
            new KeyValuePair("America/Iqaluit", -5),
            new KeyValuePair("-126:20", -32236),
            new KeyValuePair("America/Jamaica", -5),
            new KeyValuePair("-126:-120", -32120),
            new KeyValuePair("America/Merida", -6),
            new KeyValuePair("-125:-120", -31864),
            new KeyValuePair("America/Montreal", -5),
            new KeyValuePair("-127:-24", -32280),
            new KeyValuePair("America/New_York", -5),
            new KeyValuePair("-127:-112", -32368),
            new KeyValuePair("America/Port-au-Prince", -5),
            new KeyValuePair("-126:-128", -32128),
            new KeyValuePair("America/St_Johns", -3),
            new KeyValuePair("-127:-40", -32296),
            new KeyValuePair("America/St_Lucia", -4),
            new KeyValuePair("-126:-92", -32092),
            new KeyValuePair("America/Thunder_Bay", -5),
            new KeyValuePair("-127:-20", -32276),
            new KeyValuePair("Antarctica/Casey", 8),
            new KeyValuePair("-125:-104", -31848),
            new KeyValuePair("Antarctica/Davis", 7),
            new KeyValuePair("-125:-100", -31844),
            new KeyValuePair("Antarctica/McMurdo", 13),
            new KeyValuePair("-125:-80", -31824),
            new KeyValuePair("Antarctica/Syowa", 3),
            new KeyValuePair("-125:-88", -31832),
            new KeyValuePair("Asia/Almaty", 6),
            new KeyValuePair("-124:52", -31692),
            new KeyValuePair("Asia/Chongqing", 8),
            new KeyValuePair("-117:-20", -29716),
            new KeyValuePair("Asia/Hong_Kong", 8),
            new KeyValuePair("-125:-8", -31752),
            new KeyValuePair("Asia/Kathmandu", 5),
            new KeyValuePair("-116:116", -29580),
            new KeyValuePair("Asia/Kuching", 8),
            new KeyValuePair("-124:92", -31652),
            new KeyValuePair("Asia/Kuwait", 3),
            new KeyValuePair("-124:76", -31668),
            new KeyValuePair("Asia/Magadan", 11),
            new KeyValuePair("-124:-40", -31528),
            new KeyValuePair("Asia/Shanghai", 8),
            new KeyValuePair("-125:-24", -31768),
            new KeyValuePair("Asia/Thimphu", 6),
            new KeyValuePair("-125:-44", -31788),
            new KeyValuePair("Asia/Yakutsk", 9),
            new KeyValuePair("-124:-48", -31536),
            new KeyValuePair("Asia/Yerevan", 4),
            new KeyValuePair("-125:-60", -31804),
            new KeyValuePair("Atlantic/Faroe", 0),
            new KeyValuePair("-123:52", -31436),
            new KeyValuePair("Atlantic/Jan_Mayen", 1),
            new KeyValuePair("-106:52", -27084),
            new KeyValuePair("Atlantic/Madeira", 0),
            new KeyValuePair("-123:68", -31420),
            new KeyValuePair("Atlantic/South_Georgia", -2),
            new KeyValuePair("-123:48", -31440),
            new KeyValuePair("Australia/Darwin", 9),
            new KeyValuePair("-123:100", -31388),
            new KeyValuePair("Australia/Eucla", 8),
            new KeyValuePair("-123:-112", -31344),
            new KeyValuePair("Canada/East-Saskatchewan", -6),
            new KeyValuePair("-119:-4", -30212),
            new KeyValuePair("CET", 1),
            new KeyValuePair("-123:-72", -31304),
            new KeyValuePair("CST", -6),
            new KeyValuePair("-103:-108", -26220),
            new KeyValuePair("GMT+1", -1),
            new KeyValuePair("-128:64", -32704),
            new KeyValuePair("GMT-1", 1),
            new KeyValuePair("-128:60", -32708),
            new KeyValuePair("GMT-12", 12),
            new KeyValuePair("-128:16", -32752),
            new KeyValuePair("Europe/Belfast", 0),
            new KeyValuePair("-123:-56", -31288),
            new KeyValuePair("Europe/Chisinau", 2),
            new KeyValuePair("-122:36", -31196),
            new KeyValuePair("Europe/Copenhagen", 1),
            new KeyValuePair("-123:-20", -31252),
            new KeyValuePair("Europe/London", 0),
            new KeyValuePair("-123:-60", -31292),
            new KeyValuePair("Europe/Rome", 1),
            new KeyValuePair("-122:12", -31220),
            new KeyValuePair("Europe/Skopje", 1),
            new KeyValuePair("-98:112", -24976),
            new KeyValuePair("Europe/Warsaw", 1),
            new KeyValuePair("-122:56", -31176),
            new KeyValuePair("GB", 0),
            new KeyValuePair("-115:-60", -29244),
            new KeyValuePair("Indian/Maldives", 5),
            new KeyValuePair("-122:-44", -31020),
            new KeyValuePair("Israel", 2),
            new KeyValuePair("-108:40", -27608),
            new KeyValuePair("Pacific/Gambier", -9),
            new KeyValuePair("-121:28", -30948),
            new KeyValuePair("Pacific/Kosrae", 11),
            new KeyValuePair("-121:80", -30896),
            new KeyValuePair("Pacific/Norfolk", 11),
            new KeyValuePair("-121:104", -30872),
            new KeyValuePair("Pacific/Rarotonga", -10),
            new KeyValuePair("-121:20", -30956),
            new KeyValuePair("Pacific/Saipan", 10),
            new KeyValuePair("-121:56", -30920),
            new KeyValuePair("PST8PDT", -8),
            new KeyValuePair("-125:100", -31900),
            new KeyValuePair("US/Hawaii", -10),
            new KeyValuePair("-113:8", -28920),
            new KeyValuePair("W-SU", 3),
            new KeyValuePair("-114:72", -29112),
            new KeyValuePair("Africa/Algiers", 1),
            new KeyValuePair("-128:120", -32648),
            new KeyValuePair("Africa/Asmera", 3),
            new KeyValuePair("-120:-72", -30536),
            new KeyValuePair("Africa/Banjul", 0),
            new KeyValuePair("-128:-60", -32572),
            new KeyValuePair("Africa/Bujumbura", 2),
            new KeyValuePair("-128:-116", -32628),
            new KeyValuePair("Africa/Casablanca", 0),
            new KeyValuePair("-128:-12", -32524),
            new KeyValuePair("Africa/Djibouti", 3),
            new KeyValuePair("-128:-84", -32596),
            new KeyValuePair("Africa/El_Aaiun", 0),
            new KeyValuePair("-128:-8", -32520),
            new KeyValuePair("Africa/Luanda", 1),
            new KeyValuePair("-128:124", -32644),
            new KeyValuePair("America/Anchorage", -9),
            new KeyValuePair("-127:-88", -32344),
            new KeyValuePair("America/Argentina/La_Rioja", -3),
            new KeyValuePair("-127:-116", -32372),
            new KeyValuePair("America/Argentina/Mendoza", -3),
            new KeyValuePair("-118:-48", -30000),
            new KeyValuePair("America/Argentina/San_Juan", -3),
            new KeyValuePair("-125:-108", -31852),
            new KeyValuePair("America/Aruba", -4),
            new KeyValuePair("-126:-44", -32044),
            new KeyValuePair("America/Boa_Vista", -4),
            new KeyValuePair("-126:-4", -32004),
            new KeyValuePair("America/Catamarca", -3),
            new KeyValuePair("-126:-52", -32052),
            new KeyValuePair("America/Cayman", -5),
            new KeyValuePair("-126:92", -32164),
            new KeyValuePair("America/Chihuahua", -7),
            new KeyValuePair("-126:56", -32200),
            new KeyValuePair("America/Curacao", -4),
            new KeyValuePair("-125:16", -31984),
            new KeyValuePair("America/Dawson_Creek", -7),
            new KeyValuePair("-126:12", -32244),
            new KeyValuePair("America/El_Salvador", -6),
            new KeyValuePair("-126:112", -32144),
            new KeyValuePair("America/Fortaleza", -3),
            new KeyValuePair("-126:-28", -32028),
            new KeyValuePair("America/Grand_Turk", -5),
            new KeyValuePair("-126:-80", -32080),
            new KeyValuePair("America/Guayaquil", -5),
            new KeyValuePair("-125:20", -31980),
            new KeyValuePair("America/Guyana", -4),
            new KeyValuePair("-125:28", -31972),
            new KeyValuePair("America/North_Dakota/Center", -6),
            new KeyValuePair("-127:96", -32416),
            new KeyValuePair("America/Port_of_Spain", -4),
            new KeyValuePair("-125:44", -31956),
            new KeyValuePair("America/Recife", -3),
            new KeyValuePair("-127:88", -32424),
            new KeyValuePair("America/Regina", -6),
            new KeyValuePair("-127:-4", -32260),
            new KeyValuePair("America/Rio_Branco", -5),
            new KeyValuePair("-109:4", -27900),
            new KeyValuePair("America/Tegucigalpa", -6),
            new KeyValuePair("-126:-124", -32124),
            new KeyValuePair("Antarctica/Mawson", 6),
            new KeyValuePair("-125:-96", -31840),
            new KeyValuePair("Asia/Anadyr", 12),
            new KeyValuePair("-124:-32", -31520),
            new KeyValuePair("Asia/Dacca", 6),
            new KeyValuePair("-125:-48", -31792),
            new KeyValuePair("Asia/Ho_Chi_Minh", 7),
            new KeyValuePair("-116:-76", -29516),
            new KeyValuePair("Asia/Istanbul", 2),
            new KeyValuePair("-106:92", -27044),
            new KeyValuePair("Asia/Jayapura", 9),
            new KeyValuePair("-124:28", -31716),
            new KeyValuePair("Asia/Katmandu", 5),
            new KeyValuePair("-124:104", -31640),
            new KeyValuePair("Asia/Muscat", 4),
            new KeyValuePair("-124:108", -31636),
            new KeyValuePair("Asia/Phnom_Penh", 7),
            new KeyValuePair("-125:-32", -31776),
            new KeyValuePair("Asia/Pontianak", 7),
            new KeyValuePair("-124:-28", -31516),
            new KeyValuePair("Asia/Pyongyang", 9),
            new KeyValuePair("-124:72", -31672),
            new KeyValuePair("Asia/Seoul", 9),
            new KeyValuePair("-124:68", -31676),
            new KeyValuePair("Asia/Tashkent", 5),
            new KeyValuePair("-124:-80", -31568),
            new KeyValuePair("Asia/Ujung_Pandang", 8),
            new KeyValuePair("-124:24", -31720),
            new KeyValuePair("Atlantic/Cape_Verde", -1),
            new KeyValuePair("-123:76", -31412),
            new KeyValuePair("Australia/ACT", 11),
            new KeyValuePair("-115:-128", -29312),
            new KeyValuePair("Australia/North", 9),
            new KeyValuePair("-115:100", -29340),
            new KeyValuePair("Australia/Victoria", 11),
            new KeyValuePair("-115:124", -29316),
            new KeyValuePair("Canada/Atlantic", -4),
            new KeyValuePair("-119:-32", -30240),
            new KeyValuePair("Canada/Mountain", -7),
            new KeyValuePair("-118:4", -30204),
            new KeyValuePair("Canada/Newfoundland", -3),
            new KeyValuePair("-119:-40", -30248),
            new KeyValuePair("Canada/Saskatchewan", -6),
            new KeyValuePair("-111:-4", -28164),
            new KeyValuePair("EET", 2),
            new KeyValuePair("-123:-64", -31296),
            new KeyValuePair("GMT+11", -11),
            new KeyValuePair("-128:104", -32664),
            new KeyValuePair("GMT+12", -12),
            new KeyValuePair("-128:108", -32660),
            new KeyValuePair("GMT+6", -6),
            new KeyValuePair("-128:84", -32684),
            new KeyValuePair("GMT-0", 0),
            new KeyValuePair("-96:4", -24572),
            new KeyValuePair("GMT-13", 13),
            new KeyValuePair("-128:12", -32756),
            new KeyValuePair("GMT-4", 4),
            new KeyValuePair("-128:48", -32720),
            new KeyValuePair("Europe/Madrid", 1),
            new KeyValuePair("-122:80", -31152),
            new KeyValuePair("Europe/Monaco", 1),
            new KeyValuePair("-122:44", -31188),
            new KeyValuePair("Europe/Moscow", 3),
            new KeyValuePair("-122:72", -31160),
            new KeyValuePair("Europe/Oslo", 1),
            new KeyValuePair("-122:52", -31180),
            new KeyValuePair("Europe/Vaduz", 1),
            new KeyValuePair("-122:20", -31212),
            new KeyValuePair("Europe/Zurich", 1),
            new KeyValuePair("-122:88", -31144),
            new KeyValuePair("GMT0", 0),
            new KeyValuePair("-72:4", -18428),
            new KeyValuePair("Indian/Reunion", 4),
            new KeyValuePair("-122:-12", -30988),
            new KeyValuePair("Libya", 2),
            new KeyValuePair("-120:-32", -30496),
            new KeyValuePair("Navajo", -7),
            new KeyValuePair("-119:-104", -30312),
            new KeyValuePair("Pacific/Funafuti", 12),
            new KeyValuePair("-121:-112", -30832),
            new KeyValuePair("Pacific/Marquesas", -9),
            new KeyValuePair("-121:32", -30944),
            new KeyValuePair("Pacific/Pago_Pago", -11),
            new KeyValuePair("-121:120", -30856),
            new KeyValuePair("Pacific/Tarawa", 12),
            new KeyValuePair("-121:44", -30932),
            new KeyValuePair("ROK", 9),
            new KeyValuePair("-116:68", -29628),
            new KeyValuePair("Singapore", 8),
            new KeyValuePair("-116:-112", -29552),
            new KeyValuePair("US/Indiana-Starke", -5),
            new KeyValuePair("-111:-60", -28220),
            new KeyValuePair("US/Pacific", -8),
            new KeyValuePair("-119:-100", -30308),
            new KeyValuePair("WET", 0),
            new KeyValuePair("-123:-76", -31308),
            new KeyValuePair("Africa/Dakar", 0),
            new KeyValuePair("-127:20", -32492),
            new KeyValuePair("Africa/Freetown", 0),
            new KeyValuePair("-127:24", -32488),
            new KeyValuePair("Africa/Kampala", 3),
            new KeyValuePair("-127:56", -32456),
            new KeyValuePair("Africa/Lubumbashi", 2),
            new KeyValuePair("-128:-96", -32608),
            new KeyValuePair("Africa/Mbabane", 2),
            new KeyValuePair("-127:40", -32472),
            new KeyValuePair("Africa/Ndjamena", 1),
            new KeyValuePair("-128:-104", -32616),
            new KeyValuePair("America/Adak", -10),
            new KeyValuePair("-127:-80", -32336),
            new KeyValuePair("America/Anguilla", -4),
            new KeyValuePair("-126:72", -32184),
            new KeyValuePair("America/Antigua", -4),
            new KeyValuePair("-126:76", -32180),
            new KeyValuePair("America/Argentina/Jujuy", -3),
            new KeyValuePair("-118:-56", -30008),
            new KeyValuePair("America/Argentina/Rio_Gallegos", -3),
            new KeyValuePair("-127:-120", -32376),
            new KeyValuePair("America/Argentina/Ushuaia", -3),
            new KeyValuePair("-126:-64", -32064),
            new KeyValuePair("America/Atikokan", -5),
            new KeyValuePair("-125:116", -31884),
            new KeyValuePair("America/Cambridge_Bay", -7),
            new KeyValuePair("-126:28", -32228),
            new KeyValuePair("America/Caracas", -4),
            new KeyValuePair("-125:52", -31948),
            new KeyValuePair("America/Dawson", -8),
            new KeyValuePair("-126:44", -32212),
            new KeyValuePair("America/Fort_Wayne", -5),
            new KeyValuePair("-119:-68", -30276),
            new KeyValuePair("America/Glace_Bay", -4),
            new KeyValuePair("-127:-28", -32284),
            new KeyValuePair("America/Godthab", -3),
            new KeyValuePair("-125:60", -31940),
            new KeyValuePair("America/Inuvik", -7),
            new KeyValuePair("-126:36", -32220),
            new KeyValuePair("America/Jujuy", -3),
            new KeyValuePair("-126:-56", -32056),
            new KeyValuePair("America/Lima", -5),
            new KeyValuePair("-125:36", -31964),
            new KeyValuePair("America/Nassau", -5),
            new KeyValuePair("-126:80", -32176),
            new KeyValuePair("America/Noronha", -2),
            new KeyValuePair("-126:-36", -32036),
            new KeyValuePair("America/Pangnirtung", -5),
            new KeyValuePair("-126:16", -32240),
            new KeyValuePair("America/Puerto_Rico", -4),
            new KeyValuePair("-126:-100", -32100),
            new KeyValuePair("America/St_Barthelemy", -4),
            new KeyValuePair("-110:120", -28040),
            new KeyValuePair("America/St_Kitts", -4),
            new KeyValuePair("-126:-96", -32096),
            new KeyValuePair("America/Tijuana", -8),
            new KeyValuePair("-126:68", -32188),
            new KeyValuePair("Asia/Aqtau", 4),
            new KeyValuePair("-124:60", -31684),
            new KeyValuePair("Asia/Ashgabat", 5),
            new KeyValuePair("-124:-92", -31580),
            new KeyValuePair("Asia/Kamchatka", 12),
            new KeyValuePair("-124:-36", -31524),
            new KeyValuePair("Asia/Krasnoyarsk", 7),
            new KeyValuePair("-124:-56", -31544),
            new KeyValuePair("Asia/Kuala_Lumpur", 8),
            new KeyValuePair("-124:88", -31656),
            new KeyValuePair("Asia/Makassar", 8),
            new KeyValuePair("-116:24", -29672),
            new KeyValuePair("Asia/Qatar", 3),
            new KeyValuePair("-124:124", -31620),
            new KeyValuePair("Asia/Samarkand", 5),
            new KeyValuePair("-124:-84", -31572),
            new KeyValuePair("Asia/Taipei", 8),
            new KeyValuePair("-125:-4", -31748),
            new KeyValuePair("Asia/Tbilisi", 4),
            new KeyValuePair("-124:8", -31736),
            new KeyValuePair("Asia/Ulan_Bator", 8),
            new KeyValuePair("-116:100", -29596),
            new KeyValuePair("Asia/Yekaterinburg", 5),
            new KeyValuePair("-124:-68", -31556),
            new KeyValuePair("Atlantic/Reykjavik", 0),
            new KeyValuePair("-123:56", -31432),
            new KeyValuePair("Australia/Brisbane", 10),
            new KeyValuePair("-123:108", -31380),
            new KeyValuePair("Australia/Lindeman", 10),
            new KeyValuePair("-123:112", -31376),
            new KeyValuePair("Australia/Queensland", 10),
            new KeyValuePair("-115:108", -29332),
            new KeyValuePair("Australia/Yancowinna", 10),
            new KeyValuePair("-115:-124", -29308),
            new KeyValuePair("Brazil/Acre", -5),
            new KeyValuePair("-117:4", -29948),
            new KeyValuePair("Canada/Eastern", -5),
            new KeyValuePair("-119:-24", -30232),
            new KeyValuePair("EST", -5),
            new KeyValuePair("-125:76", -31924),
            new KeyValuePair("GMT-6", 6),
            new KeyValuePair("-128:40", -32728),
            new KeyValuePair("GMT-7", 7),
            new KeyValuePair("-128:36", -32732),
            new KeyValuePair("Europe/Riga", 2),
            new KeyValuePair("-122:16", -31216),
            new KeyValuePair("Europe/Sarajevo", 1),
            new KeyValuePair("-106:112", -27024),
            new KeyValuePair("Europe/Simferopol", 2),
            new KeyValuePair("-122:108", -31124),
            new KeyValuePair("Europe/Stockholm", 1),
            new KeyValuePair("-122:84", -31148),
            new KeyValuePair("Europe/Vatican", 1),
            new KeyValuePair("-114:12", -29172),
            new KeyValuePair("Europe/Vilnius", 2),
            new KeyValuePair("-122:24", -31208),
            new KeyValuePair("Hongkong", 8),
            new KeyValuePair("-117:-8", -29704),
            new KeyValuePair("Indian/Kerguelen", 5),
            new KeyValuePair("-122:-52", -31028),
            new KeyValuePair("Indian/Mahe", 4),
            new KeyValuePair("-122:-24", -31000),
            new KeyValuePair("Iran", 3),
            new KeyValuePair("-116:32", -29664),
            new KeyValuePair("Japan", 9),
            new KeyValuePair("-116:44", -29652),
            new KeyValuePair("Pacific/Guam", 10),
            new KeyValuePair("-121:40", -30936),
            new KeyValuePair("Pacific/Kiritimati", 14),
            new KeyValuePair("-121:52", -30924),
            new KeyValuePair("Pacific/Kwajalein", 12),
            new KeyValuePair("-121:64", -30912),
            new KeyValuePair("Pacific/Midway", -11),
            new KeyValuePair("-121:-104", -30824),
            new KeyValuePair("Pacific/Niue", -11),
            new KeyValuePair("-121:100", -30876),
            new KeyValuePair("Portugal", 0),
            new KeyValuePair("-114:60", -29124),
            new KeyValuePair("PST", -8),
            new KeyValuePair("-95:-100", -24164),
            new KeyValuePair("Turkey", 2),
            new KeyValuePair("-114:92", -29092),
            new KeyValuePair("US/East-Indiana", -5),
            new KeyValuePair("-111:-68", -28228),
            new KeyValuePair("US/Samoa", -11),
            new KeyValuePair("-113:120", -28808),
    };

    private static class KeyValuePair {
        String key;
        short value;

        public KeyValuePair(String key, int value) {
            this.key = key;
            this.value = (short) value;
        }

        public String key() {
            return key;
        }

        public short value() {
            return value;
        }
    }

    private static Map<String, Integer> key2index = new HashMap<String, Integer>();

    static {

        for (int index = 0; index < keyValuePairs.length; ) {
            key2index.put(keyValuePairs[index].key().toLowerCase(), index);
            key2index.put(keyValuePairs[index + 1].key(), index);
            index += 2;
        }

    }

}

