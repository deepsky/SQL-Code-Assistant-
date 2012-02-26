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

package com.deepsky.lang.plsql.formatter;

import com.intellij.formatting.Spacing;

public interface SpacingConstants {

//    Spacing NO_SPACING_WITH_NEWLINE = Spacing.createSpacing(0, 0, 0, true, 1);
    Spacing NO_SPACING_WITH_NEWLINE = Spacing.createSpacing(0, 0, 0, true, 0);

    // No spaces, NO newline, NO blank lines
    Spacing NO_SPACING = Spacing.createSpacing(0, 0, 0, false, 0);

    // One whitespace, NO newlines
    Spacing ONE_SPACE_WITHOUT_NEWLINE = Spacing.createSpacing(1, 1, 0, false, 0);

    // One whitespace, DON'T keep blank lines
    Spacing ONE_SPACE_WITH_NEWLINE = Spacing.createSpacing(1, 1, 0, true, 0);

    // One whitespace, keep blank lines
    Spacing COMMON_SPACING = Spacing.createSpacing(1, 1, 0, true, 100);

    Spacing COMMON_SPACING_WITH_NL = Spacing.createSpacing(1, 1, 1, true, 100);
    Spacing IMPORT_BETWEEN_SPACING = Spacing.createSpacing(0, 0, 1, true, 100);
    Spacing IMPORT_OTHER_SPACING = Spacing.createSpacing(0, 0, 2, true, 100);
    Spacing LAZY_SPACING = Spacing.createSpacing(0, 239, 0, true, 100);

}
