package com.deepsky.generated.plsql;

import java.util.Map;
import java.util.HashMap;

public class SymbolTable {

	public static Integer get(String text){
		return keywords.get(text.toLowerCase());
	}

	static public Map<String, Integer> keywords;
	static {
		keywords = new HashMap<String, Integer>();
		keywords.put("using", 420);
		keywords.put("error_index", 564);
		keywords.put("store", 482);
		keywords.put("referencing", 447);
		keywords.put("fipsflag", 616);
		keywords.put("external", 495);
		keywords.put("wait", 691);
		keywords.put("pctfree", 473);
		keywords.put("float", 596);
		keywords.put("lrtrim", 727);
		keywords.put("missing", 725);
		keywords.put("references", 370);
		keywords.put("time", 582);
		keywords.put("over", 649);
		keywords.put("characterset", 705);
		keywords.put("movement", 367);
		keywords.put("role", 350);
		keywords.put("logon", 427);
		keywords.put("right", 673);
		keywords.put("else", 568);
		keywords.put("monitoring", 477);
		keywords.put("savepoint", 698);
		keywords.put("number", 575);
		keywords.put("extract", 653);
		keywords.put("nocompress", 472);
		keywords.put("novalidate", 490);
		keywords.put("diassociate", 435);
		keywords.put("sysdate", 361);
		keywords.put("subtype", 543);
		keywords.put("each", 446);
		keywords.put("view", 334);
		keywords.put("big", 706);
		keywords.put("serially_reusable", 611);
		keywords.put("nextval", 647);
		keywords.put("unique", 452);
		keywords.put("direct_load", 470);
		keywords.put("raise", 549);
		keywords.put("before", 423);
		keywords.put("exclusive", 697);
		keywords.put("sqlerrm", 640);
		keywords.put("instances", 497);
		keywords.put("nowait", 690);
		keywords.put("preprocessor", 746);
		keywords.put("loop", 544);
		keywords.put("current", 632);
		keywords.put("left", 672);
		keywords.put("shutdown", 425);
		keywords.put("member", 637);
		keywords.put("define", 389);
		keywords.put("sid", 671);
		keywords.put("restrict_references", 613);
		keywords.put("partitions", 489);
		keywords.put("integer", 592);
		keywords.put("hour", 645);
		keywords.put("join", 676);
		keywords.put("operator", 348);
		keywords.put("analyze", 429);
		keywords.put("ref", 531);
		keywords.put("new", 450);
		keywords.put("including", 492);
		keywords.put("sequence", 343);
		keywords.put("library", 352);
		keywords.put("rem", 391);
		keywords.put("exists", 633);
		keywords.put("having", 686);
		keywords.put("public", 346);
		keywords.put("zone", 583);
		keywords.put("sizes", 712);
		keywords.put("body", 340);
		keywords.put("visible", 526);
		keywords.put("drop", 331);
		keywords.put("exception", 610);
		keywords.put("by", 413);
		keywords.put("long", 373);
		keywords.put("close", 555);
		keywords.put("any", 646);
		keywords.put("key", 369);
		keywords.put("nobadfile", 715);
		keywords.put("execute", 379);
		keywords.put("oserror", 382);
		keywords.put("double", 594);
		keywords.put("repheader", 398);
		keywords.put("and", 631);
		keywords.put("compress", 468);
		keywords.put("delimited", 703);
		keywords.put("overflow", 487);
		keywords.put("autonomous_transaction", 570);
		keywords.put("column", 359);
		keywords.put("day", 586);
		keywords.put("collect", 663);
		keywords.put("update", 444);
		keywords.put("raw", 578);
		keywords.put("connect", 417);
		keywords.put("nologging", 466);
		keywords.put("timezone_hour", 655);
		keywords.put("var", 375);
		keywords.put("set", 372);
		keywords.put("data_cache", 723);
		keywords.put("ddl", 434);
		keywords.put("statistics", 431);
		keywords.put("organization", 491);
		keywords.put("name", 619);
		keywords.put("disable", 364);
		keywords.put("trim", 641);
		keywords.put("all", 469);
		keywords.put("parallel", 463);
		keywords.put("nodiscardfile", 717);
		keywords.put("constant", 542);
		keywords.put("precision", 595);
		keywords.put("oracle_loader", 759);
		keywords.put("at", 630);
		keywords.put("as", 449);
		keywords.put("audit", 432);
		keywords.put("flush", 668);
		keywords.put("cascade", 335);
		keywords.put("off", 394);
		keywords.put("java", 618);
		keywords.put("disabled", 749);
		keywords.put("enclosed", 733);
		keywords.put("no", 518);
		keywords.put("package", 339);
		keywords.put("nocache", 411);
		keywords.put("fixed", 704);
		keywords.put("of", 441);
		keywords.put("rank", 642);
		keywords.put("byte", 577);
		keywords.put("on", 357);
		keywords.put("only", 538);
		keywords.put("reset", 670);
		keywords.put("purge", 333);
		keywords.put("**", 629);
		keywords.put("limit", 499);
		keywords.put("increment", 412);
		keywords.put("fetch", 556);
		keywords.put("or", 404);
		keywords.put("varrawc", 737);
		keywords.put("startup", 424);
		keywords.put("row", 366);
		keywords.put("noorder", 416);
		keywords.put("newline", 702);
		keywords.put("session", 667);
		keywords.put("endian", 708);
		keywords.put("then", 567);
		keywords.put("month", 585);
		keywords.put("logoff", 428);
		keywords.put("comment", 356);
		keywords.put("records", 699);
		keywords.put("interval", 481);
		keywords.put("sqlcode", 639);
		keywords.put("merge", 552);
		keywords.put("pctthreshold", 493);
		keywords.put("constraint", 371);
		keywords.put("quit", 392);
		keywords.put("rownum", 683);
		keywords.put("zoned", 741);
		keywords.put("null", 363);
		keywords.put("rowcount", 560);
		keywords.put("true", 627);
		keywords.put("ldtrim", 731);
		keywords.put("sql", 557);
		keywords.put("discardfile", 718);
		keywords.put("force", 342);
		keywords.put("insert", 443);
		keywords.put("count", 566);
		keywords.put("second", 587);
		keywords.put("save", 624);
		keywords.put("last", 689);
		keywords.put("location", 743);
		keywords.put("char", 576);
		keywords.put("type", 344);
		keywords.put("where", 677);
		keywords.put("authid", 539);
		keywords.put("prior", 571);
		keywords.put("maxextents", 505);
		keywords.put("revoke", 438);
		keywords.put("partition", 479);
		keywords.put("spool", 393);
		keywords.put("when", 451);
		keywords.put("primary", 368);
		keywords.put("action", 519);
		keywords.put("none", 387);
		keywords.put("minvalue", 407);
		keywords.put("cycle", 408);
		keywords.put("returning", 693);
		keywords.put("pctused", 474);
		keywords.put("minus", 661);
		keywords.put("trailing", 679);
		keywords.put("int", 591);
		keywords.put("error_code", 565);
		keywords.put("rows", 459);
		keywords.put("intersect", 660);
		keywords.put("nomonitoring", 478);
		keywords.put("serveroutput", 399);
		keywords.put("readsize", 721);
		keywords.put("nosort", 524);
		keywords.put("rollback", 386);
		keywords.put("from", 654);
		keywords.put("add", 520);
		keywords.put("online", 461);
		keywords.put("while", 545);
		keywords.put("real", 589);
		keywords.put("if", 547);
		keywords.put("read", 537);
		keywords.put("compute", 462);
		keywords.put("less", 485);
		keywords.put("between", 636);
		keywords.put("is", 358);
		keywords.put("rtrim", 730);
		keywords.put("rowtype", 534);
		keywords.put("into", 664);
		keywords.put("modify", 521);
		keywords.put("mlslabel", 603);
		keywords.put("interface", 614);
		keywords.put("concat", 744);
		keywords.put("in", 483);
		keywords.put("database", 353);
		keywords.put("systimestamp", 681);
		keywords.put("local", 528);
		keywords.put("found", 558);
		keywords.put("varraw", 735);
		keywords.put("matched", 692);
		keywords.put("varray", 535);
		keywords.put("nulls", 687);
		keywords.put("optimal", 510);
		keywords.put("validate", 345);
		keywords.put("associate", 430);
		keywords.put("schema", 439);
		keywords.put("buffer_pool", 511);
		keywords.put("freelists", 507);
		keywords.put("boolean", 579);
		keywords.put("year", 584);
		keywords.put("pipelined", 620);
		keywords.put("option", 536);
		keywords.put("nvarchar", 600);
		keywords.put("whitespace", 756);
		keywords.put("constraints", 336);
		keywords.put("charset", 609);
		keywords.put("optionally", 757);
		keywords.put("declare", 401);
		keywords.put("pragma", 569);
		keywords.put("dbtimezone", 651);
		keywords.put("system", 666);
		keywords.put("leading", 678);
		keywords.put("pls_integer", 593);
		keywords.put("load", 714);
		keywords.put("pctincrease", 506);
		keywords.put("exception_init", 612);
		keywords.put("sort", 523);
		keywords.put("desc", 455);
		keywords.put("oracle_datapump", 760);
		keywords.put("next", 503);
		keywords.put("clob", 605);
		keywords.put("data", 753);
		keywords.put("repfooter", 397);
		keywords.put("unlimited", 500);
		keywords.put("oracle_number", 738);
		keywords.put("date", 580);
		keywords.put("timestamp", 581);
		keywords.put("elsif", 658);
		keywords.put("builtin", 615);
		keywords.put("whenever", 380);
		keywords.put("parallel_enable", 621);
		keywords.put("procedure", 338);
		keywords.put("varchar", 598);
		keywords.put("reverse", 525);
		keywords.put("binary_integer", 572);
		keywords.put("currval", 648);
		keywords.put("varchar2", 599);
		keywords.put("bulk", 662);
		keywords.put("alter", 330);
		keywords.put("field", 726);
		keywords.put("replace", 405);
		keywords.put("oracle_date", 739);
		keywords.put("noparallel", 464);
		keywords.put("string", 711);
		keywords.put("prompt", 390);
		keywords.put("variable", 376);
		keywords.put("keep", 512);
		keywords.put("shared_pool", 669);
		keywords.put("to", 418);
		keywords.put("col", 377);
		keywords.put("both", 680);
		keywords.put("synonym", 347);
		keywords.put("inner", 674);
		keywords.put("nologfile", 719);
		keywords.put("identified", 419);
		keywords.put("after", 422);
		keywords.put("timezone", 758);
		keywords.put("values", 484);
		keywords.put("commit", 385);
		keywords.put("encrypt", 514);
		keywords.put("sessiontimezone", 650);
		keywords.put("ldrtrim", 765);
		keywords.put("parameters", 700);
		keywords.put("fields", 724);
		keywords.put("enabled", 748);
		keywords.put("index", 341);
		keywords.put("bitmap", 453);
		keywords.put("sta", 395);
		keywords.put("timezone_minute", 656);
		keywords.put("select", 551);
		keywords.put("maxvalue", 406);
		keywords.put("cast", 638);
		keywords.put("than", 486);
		keywords.put("exec", 378);
		keywords.put("case", 550);
		keywords.put("foreign", 516);
		keywords.put("natural", 573);
		keywords.put("freelist", 508);
		keywords.put("compatible", 752);
		keywords.put("date_format", 734);
		keywords.put("novisible", 527);
		keywords.put("bulk_exceptions", 563);
		keywords.put("logfile", 720);
		keywords.put("lobfile", 745);
		keywords.put("filesystem_like_logging", 467);
		keywords.put("nocopy", 607);
		keywords.put("immediate", 665);
		keywords.put("counted", 740);
		keywords.put("operations", 471);
		keywords.put("latest", 761);
		keywords.put("smallint", 588);
		keywords.put("out", 606);
		keywords.put("aggregate", 623);
		keywords.put("cursor", 532);
		keywords.put("wrapped", 540);
		keywords.put("numeric", 590);
		keywords.put("for", 445);
		keywords.put("distinct", 652);
		keywords.put("open", 554);
		keywords.put("are", 763);
		keywords.put("initial", 502);
		keywords.put("maxtrans", 476);
		keywords.put("noaudit", 433);
		keywords.put("any_cs", 608);
		keywords.put("false", 628);
		keywords.put("compression", 747);
		keywords.put("groups", 509);
		keywords.put("table", 332);
		keywords.put("like", 634);
		keywords.put("create", 403);
		keywords.put("exit", 383);
		keywords.put("not", 362);
		keywords.put("record", 530);
		keywords.put("recycle", 513);
		keywords.put("asc", 454);
		keywords.put("start", 396);
		keywords.put("truncate", 355);
		keywords.put("indices", 626);
		keywords.put("language", 617);
		keywords.put("position", 732);
		keywords.put("notrim", 728);
		keywords.put("bytes", 713);
		keywords.put("range", 480);
		keywords.put("initrans", 475);
		keywords.put("link", 354);
		keywords.put("goto", 548);
		keywords.put("nocheck", 710);
		keywords.put("escape", 635);
		keywords.put("version", 751);
		keywords.put("character", 602);
		keywords.put("mode", 695);
		keywords.put("badfile", 716);
		keywords.put("exceptions", 625);
		keywords.put("def", 388);
		keywords.put("union", 659);
		keywords.put("delete", 442);
		keywords.put("bulk_rowcount", 562);
		keywords.put("deterministic", 622);
		keywords.put("end", 541);
		keywords.put("trigger", 421);
		keywords.put("isopen", 561);
		keywords.put("rely", 522);
		keywords.put("cache", 410);
		keywords.put("return", 533);
		keywords.put("unsigned", 742);
		keywords.put("access", 701);
		keywords.put("transforms", 764);
		keywords.put("current_timestamp", 682);
		keywords.put("degree", 496);
		keywords.put("directory", 351);
		keywords.put("terminated", 755);
		keywords.put("minextents", 504);
		keywords.put("old", 448);
		keywords.put("grant", 436);
		keywords.put("nvarchar2", 601);
		keywords.put("transaction", 694);
		keywords.put("rename", 437);
		keywords.put("enable", 365);
		keywords.put("mask", 754);
		keywords.put("little", 707);
		keywords.put("show", 374);
		keywords.put("skip", 722);
		keywords.put("preserve", 458);
		keywords.put("function", 337);
		keywords.put("heap", 494);
		keywords.put("tablespace", 460);
		keywords.put("encryption", 750);
		keywords.put("positive", 574);
		keywords.put("work", 657);
		keywords.put("hash", 488);
		keywords.put("varcharc", 736);
		keywords.put("global", 456);
		keywords.put("logging", 465);
		keywords.put("mark", 709);
		keywords.put("forall", 546);
		keywords.put("restrict", 517);
		keywords.put("default", 360);
		keywords.put("dense_rank", 643);
		keywords.put("characters", 762);
		keywords.put("reject", 498);
		keywords.put("temporary", 457);
		keywords.put("servererror", 426);
		keywords.put("object", 529);
		keywords.put("minute", 644);
		keywords.put("share", 696);
		keywords.put("order", 415);
		keywords.put("ltrim", 729);
		keywords.put("notfound", 559);
		keywords.put("with", 414);
		keywords.put("check", 515);
		keywords.put("lock", 553);
		keywords.put("the", 684);
		keywords.put("sqlerror", 381);
		keywords.put("decimal", 597);
		keywords.put("begin", 400);
		keywords.put("blob", 604);
		keywords.put("nocycle", 409);
		keywords.put("outer", 675);
		keywords.put("continue", 384);
		keywords.put("instead", 440);
		keywords.put("group", 685);
		keywords.put("user", 349);
		keywords.put("first", 688);
		keywords.put("storage", 501);
	}
}