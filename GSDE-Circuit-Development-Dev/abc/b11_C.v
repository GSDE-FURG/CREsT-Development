// Benchmark "b11_C" written by ABC on Wed Aug 05 14:39:55 2020

module b11_C ( 
    X_IN_5_, X_IN_4_, X_IN_3_, X_IN_2_, X_IN_1_, X_IN_0_, STBI,
    STATO_REG_0__SCAN_IN, STATO_REG_1__SCAN_IN, STATO_REG_2__SCAN_IN,
    STATO_REG_3__SCAN_IN, X_OUT_REG_0__SCAN_IN, X_OUT_REG_1__SCAN_IN,
    X_OUT_REG_2__SCAN_IN, X_OUT_REG_3__SCAN_IN, X_OUT_REG_4__SCAN_IN,
    X_OUT_REG_5__SCAN_IN, CONT1_REG_0__SCAN_IN, CONT1_REG_1__SCAN_IN,
    R_IN_REG_5__SCAN_IN, R_IN_REG_4__SCAN_IN, R_IN_REG_3__SCAN_IN,
    R_IN_REG_2__SCAN_IN, R_IN_REG_1__SCAN_IN, R_IN_REG_0__SCAN_IN,
    CONT_REG_5__SCAN_IN, CONT_REG_4__SCAN_IN, CONT_REG_3__SCAN_IN,
    CONT_REG_2__SCAN_IN, CONT_REG_1__SCAN_IN, CONT_REG_0__SCAN_IN,
    CONT1_REG_8__SCAN_IN, CONT1_REG_7__SCAN_IN, CONT1_REG_6__SCAN_IN,
    CONT1_REG_5__SCAN_IN, CONT1_REG_4__SCAN_IN, CONT1_REG_3__SCAN_IN,
    CONT1_REG_2__SCAN_IN,
    U404, U405, U406, U407, U408, U409, U384, U383, U382, U381, U380, U379,
    U378, U377, U376, U375, U374, U373, U372, U371, U370, U369, U368, U367,
    U366, U365, U364, U360, U361, U362, U363  );
  input  X_IN_5_, X_IN_4_, X_IN_3_, X_IN_2_, X_IN_1_, X_IN_0_, STBI,
    STATO_REG_0__SCAN_IN, STATO_REG_1__SCAN_IN, STATO_REG_2__SCAN_IN,
    STATO_REG_3__SCAN_IN, X_OUT_REG_0__SCAN_IN, X_OUT_REG_1__SCAN_IN,
    X_OUT_REG_2__SCAN_IN, X_OUT_REG_3__SCAN_IN, X_OUT_REG_4__SCAN_IN,
    X_OUT_REG_5__SCAN_IN, CONT1_REG_0__SCAN_IN, CONT1_REG_1__SCAN_IN,
    R_IN_REG_5__SCAN_IN, R_IN_REG_4__SCAN_IN, R_IN_REG_3__SCAN_IN,
    R_IN_REG_2__SCAN_IN, R_IN_REG_1__SCAN_IN, R_IN_REG_0__SCAN_IN,
    CONT_REG_5__SCAN_IN, CONT_REG_4__SCAN_IN, CONT_REG_3__SCAN_IN,
    CONT_REG_2__SCAN_IN, CONT_REG_1__SCAN_IN, CONT_REG_0__SCAN_IN,
    CONT1_REG_8__SCAN_IN, CONT1_REG_7__SCAN_IN, CONT1_REG_6__SCAN_IN,
    CONT1_REG_5__SCAN_IN, CONT1_REG_4__SCAN_IN, CONT1_REG_3__SCAN_IN,
    CONT1_REG_2__SCAN_IN;
  output U404, U405, U406, U407, U408, U409, U384, U383, U382, U381, U380,
    U379, U378, U377, U376, U375, U374, U373, U372, U371, U370, U369, U368,
    U367, U366, U365, U364, U360, U361, U362, U363;
  wire n75, n76, n77, n79, n80, n82, n83, n85, n86, n88, n89, n91, n92, n94,
    n95, n96, n97, n98, n99, n100, n101, n102, n103, n104, n105, n106,
    n107, n108, n109, n110, n111, n112, n113, n114, n115, n116, n117, n118,
    n119, n120, n121, n122, n123, n124, n125, n126, n127, n128, n129, n130,
    n131, n132, n133, n134, n135, n137, n139, n140, n141, n143, n144, n145,
    n147, n148, n150, n152, n153, n154, n155, n156, n157, n158, n159, n160,
    n162, n163, n164, n165, n166, n167, n168, n169, n170, n171, n172, n173,
    n174, n175, n176, n177, n178, n179, n180, n181, n182, n183, n184, n185,
    n186, n187, n188, n189, n190, n191, n192, n193, n194, n195, n196, n197,
    n198, n199, n200, n201, n202, n203, n204, n205, n206, n207, n208, n209,
    n210, n211, n212, n213, n214, n215, n217, n218, n219, n220, n221, n222,
    n223, n224, n225, n226, n227, n228, n229, n230, n231, n232, n233, n234,
    n235, n236, n237, n238, n239, n240, n241, n242, n243, n244, n245, n246,
    n247, n248, n249, n250, n251, n252, n253, n254, n255, n256, n257, n258,
    n259, n260, n261, n262, n263, n264, n265, n266, n267, n269, n270, n271,
    n272, n273, n274, n275, n276, n277, n278, n279, n280, n281, n283, n284,
    n285, n286, n287, n288, n289, n291, n292, n293, n294, n295, n296, n297,
    n298, n299, n300, n302, n303, n304, n305, n306, n307, n308, n309, n310,
    n311, n312, n313, n314, n315, n318, n319, n320, n321, n322, n323, n324,
    n326, n327, n328, n329, n330, n331, n332, n333, n334, n335, n336, n337,
    n338, n339, n340, n341, n342, n343, n344, n345, n347, n348, n349, n350,
    n351, n352, n353, n354, n355, n356, n357, n358, n360, n361, n362, n364,
    n365, n366, n367, n368, n369, n372, n373, n374, n375, n376, n377, n378,
    n380, n383, n384, n385, n386, n388, n389, n390, n391, n392, n393, n394,
    n396, n397, n398, n399, n400, n401, n402, n403, n404, n405, n406, n407,
    n408, n409, n411, n412, n413, n414, n416, n417, n418, n420, n422, n424,
    n425, n427, n428, n430, n433, n434, n435, n436, n437, n438, n440, n441,
    n442, n443;
  INVX1   g000(.A(R_IN_REG_5__SCAN_IN), .Y(n75));
  NOR3X1  g001(.A(STATO_REG_3__SCAN_IN), .B(STATO_REG_2__SCAN_IN), .C(STATO_REG_1__SCAN_IN), .Y(n76));
  NAND2X1 g002(.A(n76), .B(X_IN_5_), .Y(n77));
  OAI21X1 g003(.A0(n76), .A1(n75), .B0(n77), .Y(U404));
  INVX1   g004(.A(R_IN_REG_4__SCAN_IN), .Y(n79));
  NAND2X1 g005(.A(n76), .B(X_IN_4_), .Y(n80));
  OAI21X1 g006(.A0(n76), .A1(n79), .B0(n80), .Y(U405));
  INVX1   g007(.A(R_IN_REG_3__SCAN_IN), .Y(n82));
  NAND2X1 g008(.A(n76), .B(X_IN_3_), .Y(n83));
  OAI21X1 g009(.A0(n76), .A1(n82), .B0(n83), .Y(U406));
  INVX1   g010(.A(R_IN_REG_2__SCAN_IN), .Y(n85));
  NAND2X1 g011(.A(n76), .B(X_IN_2_), .Y(n86));
  OAI21X1 g012(.A0(n76), .A1(n85), .B0(n86), .Y(U407));
  INVX1   g013(.A(R_IN_REG_1__SCAN_IN), .Y(n88));
  NAND2X1 g014(.A(n76), .B(X_IN_1_), .Y(n89));
  OAI21X1 g015(.A0(n76), .A1(n88), .B0(n89), .Y(U408));
  INVX1   g016(.A(R_IN_REG_0__SCAN_IN), .Y(n91));
  NAND2X1 g017(.A(n76), .B(X_IN_0_), .Y(n92));
  OAI21X1 g018(.A0(n76), .A1(n91), .B0(n92), .Y(U409));
  INVX1   g019(.A(CONT_REG_5__SCAN_IN), .Y(n94));
  INVX1   g020(.A(STATO_REG_3__SCAN_IN), .Y(n95));
  INVX1   g021(.A(STATO_REG_1__SCAN_IN), .Y(n96));
  NOR3X1  g022(.A(STATO_REG_2__SCAN_IN), .B(n96), .C(STATO_REG_0__SCAN_IN), .Y(n97));
  AOI22X1 g023(.A0(R_IN_REG_3__SCAN_IN), .A1(n88), .B0(R_IN_REG_4__SCAN_IN), .B1(n75), .Y(n98));
  INVX1   g024(.A(n98), .Y(n99));
  OAI22X1 g025(.A0(n88), .A1(R_IN_REG_2__SCAN_IN), .B0(R_IN_REG_4__SCAN_IN), .B1(n91), .Y(n100));
  OAI22X1 g026(.A0(n85), .A1(R_IN_REG_0__SCAN_IN), .B0(R_IN_REG_3__SCAN_IN), .B1(n75), .Y(n101));
  NOR3X1  g027(.A(n101), .B(n100), .C(n99), .Y(n102));
  NOR3X1  g028(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .C(STATO_REG_0__SCAN_IN), .Y(n103));
  AOI22X1 g029(.A0(n102), .A1(n97), .B0(n95), .B1(n103), .Y(n104));
  INVX1   g030(.A(n104), .Y(n105));
  INVX1   g031(.A(CONT_REG_4__SCAN_IN), .Y(n106));
  NAND4X1 g032(.A(CONT_REG_1__SCAN_IN), .B(CONT_REG_2__SCAN_IN), .C(CONT_REG_3__SCAN_IN), .D(CONT_REG_0__SCAN_IN), .Y(n107));
  NOR2X1  g033(.A(n107), .B(n106), .Y(n108));
  XOR2X1  g034(.A(n108), .B(n94), .Y(n109));
  INVX1   g035(.A(n102), .Y(n110));
  NAND2X1 g036(.A(n102), .B(CONT_REG_5__SCAN_IN), .Y(n111));
  AOI21X1 g037(.A0(n110), .A1(R_IN_REG_5__SCAN_IN), .B0(n111), .Y(n112));
  NOR2X1  g038(.A(n102), .B(n88), .Y(n113));
  NOR4X1  g039(.A(n100), .B(n99), .C(CONT_REG_1__SCAN_IN), .D(n101), .Y(n114));
  NOR2X1  g040(.A(n102), .B(R_IN_REG_0__SCAN_IN), .Y(n115));
  AOI21X1 g041(.A0(n102), .A1(CONT_REG_0__SCAN_IN), .B0(n115), .Y(n116));
  OAI21X1 g042(.A0(n114), .A1(n113), .B0(n116), .Y(n117));
  NAND2X1 g043(.A(n102), .B(CONT_REG_2__SCAN_IN), .Y(n118));
  NOR2X1  g044(.A(n102), .B(n85), .Y(n119));
  AOI22X1 g045(.A0(n118), .A1(n119), .B0(n114), .B1(n113), .Y(n120));
  NOR2X1  g046(.A(n102), .B(R_IN_REG_3__SCAN_IN), .Y(n121));
  NOR4X1  g047(.A(n100), .B(n99), .C(CONT_REG_3__SCAN_IN), .D(n101), .Y(n122));
  INVX1   g048(.A(n122), .Y(n123));
  NAND2X1 g049(.A(n123), .B(n121), .Y(n124));
  OAI21X1 g050(.A0(n119), .A1(n118), .B0(n124), .Y(n125));
  AOI21X1 g051(.A0(n120), .A1(n117), .B0(n125), .Y(n126));
  NOR4X1  g052(.A(n100), .B(n99), .C(CONT_REG_4__SCAN_IN), .D(n101), .Y(n127));
  OAI21X1 g053(.A0(n102), .A1(R_IN_REG_4__SCAN_IN), .B0(n127), .Y(n128));
  OAI21X1 g054(.A0(n123), .A1(n121), .B0(n128), .Y(n129));
  NOR2X1  g055(.A(n102), .B(R_IN_REG_4__SCAN_IN), .Y(n130));
  OAI21X1 g056(.A0(n110), .A1(CONT_REG_4__SCAN_IN), .B0(n130), .Y(n131));
  OAI21X1 g057(.A0(n129), .A1(n126), .B0(n131), .Y(n132));
  NAND3X1 g058(.A(n111), .B(n110), .C(R_IN_REG_5__SCAN_IN), .Y(n133));
  AOI21X1 g059(.A0(n133), .A1(n132), .B0(n112), .Y(n134));
  NAND3X1 g060(.A(n134), .B(n105), .C(STATO_REG_1__SCAN_IN), .Y(n135));
  OAI22X1 g061(.A0(n109), .A1(n135), .B0(n105), .B1(n94), .Y(U384));
  XOR2X1  g062(.A(n107), .B(CONT_REG_4__SCAN_IN), .Y(n137));
  OAI22X1 g063(.A0(n135), .A1(n137), .B0(n105), .B1(n106), .Y(U383));
  INVX1   g064(.A(CONT_REG_3__SCAN_IN), .Y(n139));
  NAND3X1 g065(.A(CONT_REG_0__SCAN_IN), .B(CONT_REG_1__SCAN_IN), .C(CONT_REG_2__SCAN_IN), .Y(n140));
  XOR2X1  g066(.A(n140), .B(CONT_REG_3__SCAN_IN), .Y(n141));
  OAI22X1 g067(.A0(n135), .A1(n141), .B0(n105), .B1(n139), .Y(U382));
  INVX1   g068(.A(CONT_REG_2__SCAN_IN), .Y(n143));
  NAND2X1 g069(.A(CONT_REG_0__SCAN_IN), .B(CONT_REG_1__SCAN_IN), .Y(n144));
  XOR2X1  g070(.A(n144), .B(CONT_REG_2__SCAN_IN), .Y(n145));
  OAI22X1 g071(.A0(n135), .A1(n145), .B0(n105), .B1(n143), .Y(U381));
  INVX1   g072(.A(CONT_REG_1__SCAN_IN), .Y(n147));
  XOR2X1  g073(.A(CONT_REG_0__SCAN_IN), .B(n147), .Y(n148));
  OAI22X1 g074(.A0(n135), .A1(n148), .B0(n105), .B1(n147), .Y(U380));
  NAND2X1 g075(.A(n104), .B(CONT_REG_0__SCAN_IN), .Y(n150));
  OAI21X1 g076(.A0(n135), .A1(CONT_REG_0__SCAN_IN), .B0(n150), .Y(U379));
  XOR2X1  g077(.A(CONT1_REG_8__SCAN_IN), .B(R_IN_REG_3__SCAN_IN), .Y(n152));
  INVX1   g078(.A(n152), .Y(n153));
  INVX1   g079(.A(CONT1_REG_7__SCAN_IN), .Y(n154));
  INVX1   g080(.A(CONT1_REG_6__SCAN_IN), .Y(n155));
  XOR2X1  g081(.A(R_IN_REG_2__SCAN_IN), .B(R_IN_REG_3__SCAN_IN), .Y(n156));
  XOR2X1  g082(.A(n156), .B(R_IN_REG_3__SCAN_IN), .Y(n157));
  NAND2X1 g083(.A(n157), .B(CONT1_REG_4__SCAN_IN), .Y(n158));
  NOR2X1  g084(.A(n157), .B(CONT1_REG_4__SCAN_IN), .Y(n159));
  INVX1   g085(.A(CONT1_REG_3__SCAN_IN), .Y(n160));
  NOR2X1  g086(.A(n156), .B(n160), .Y(n162));
  NAND2X1 g087(.A(n156), .B(n160), .Y(n163));
  OAI21X1 g088(.A0(R_IN_REG_2__SCAN_IN), .A1(R_IN_REG_3__SCAN_IN), .B0(CONT1_REG_2__SCAN_IN), .Y(n164));
  NOR3X1  g089(.A(CONT1_REG_2__SCAN_IN), .B(R_IN_REG_2__SCAN_IN), .C(R_IN_REG_3__SCAN_IN), .Y(n165));
  NAND2X1 g090(.A(n156), .B(CONT1_REG_0__SCAN_IN), .Y(n166));
  OAI21X1 g091(.A0(R_IN_REG_2__SCAN_IN), .A1(CONT1_REG_0__SCAN_IN), .B0(n82), .Y(n167));
  AOI21X1 g092(.A0(n167), .A1(n166), .B0(n157), .Y(n168));
  NAND3X1 g093(.A(n167), .B(n166), .C(n157), .Y(n169));
  AOI21X1 g094(.A0(n169), .A1(CONT1_REG_1__SCAN_IN), .B0(n168), .Y(n170));
  OAI21X1 g095(.A0(n170), .A1(n165), .B0(n164), .Y(n171));
  AOI21X1 g096(.A0(n171), .A1(n163), .B0(n162), .Y(n172));
  OAI21X1 g097(.A0(n172), .A1(n159), .B0(n158), .Y(n173));
  NAND2X1 g098(.A(n173), .B(CONT1_REG_5__SCAN_IN), .Y(n174));
  NAND2X1 g099(.A(n85), .B(n82), .Y(n175));
  INVX1   g100(.A(n175), .Y(n176));
  OAI21X1 g101(.A0(n173), .A1(CONT1_REG_5__SCAN_IN), .B0(n176), .Y(n177));
  AOI21X1 g102(.A0(n177), .A1(n174), .B0(n155), .Y(n178));
  NAND3X1 g103(.A(n177), .B(n174), .C(n155), .Y(n179));
  AOI21X1 g104(.A0(n179), .A1(n82), .B0(n178), .Y(n180));
  NOR2X1  g105(.A(n180), .B(n154), .Y(n181));
  AOI21X1 g106(.A0(n180), .A1(n154), .B0(R_IN_REG_3__SCAN_IN), .Y(n182));
  NOR3X1  g107(.A(n182), .B(n181), .C(n153), .Y(n183));
  OAI21X1 g108(.A0(n182), .A1(n181), .B0(n153), .Y(n184));
  INVX1   g109(.A(STATO_REG_0__SCAN_IN), .Y(n185));
  INVX1   g110(.A(STATO_REG_2__SCAN_IN), .Y(n186));
  INVX1   g111(.A(CONT1_REG_8__SCAN_IN), .Y(n187));
  INVX1   g112(.A(CONT1_REG_4__SCAN_IN), .Y(n188));
  AOI21X1 g113(.A0(CONT1_REG_1__SCAN_IN), .A1(CONT1_REG_0__SCAN_IN), .B0(CONT1_REG_2__SCAN_IN), .Y(n189));
  NOR3X1  g114(.A(n189), .B(n160), .C(n188), .Y(n190));
  INVX1   g115(.A(CONT1_REG_5__SCAN_IN), .Y(n191));
  NAND3X1 g116(.A(n191), .B(n155), .C(n154), .Y(n192));
  OAI21X1 g117(.A0(n192), .A1(n190), .B0(n187), .Y(n193));
  AOI21X1 g118(.A0(n155), .A1(n154), .B0(CONT1_REG_8__SCAN_IN), .Y(n194));
  OAI21X1 g119(.A0(n194), .A1(n96), .B0(STATO_REG_2__SCAN_IN), .Y(n195));
  AOI21X1 g120(.A0(n193), .A1(STATO_REG_0__SCAN_IN), .B0(n195), .Y(n196));
  NAND2X1 g121(.A(n186), .B(STATO_REG_1__SCAN_IN), .Y(n197));
  NOR4X1  g122(.A(n100), .B(n99), .C(n197), .D(n101), .Y(n198));
  NOR3X1  g123(.A(n186), .B(n96), .C(n185), .Y(n199));
  NAND2X1 g124(.A(n199), .B(n184), .Y(n200));
  NOR2X1  g125(.A(n191), .B(n95), .Y(n201));
  NAND2X1 g126(.A(CONT1_REG_8__SCAN_IN), .B(n95), .Y(n202));
  XOR2X1  g127(.A(n202), .B(n201), .Y(n203));
  NOR2X1  g128(.A(n155), .B(STATO_REG_3__SCAN_IN), .Y(n204));
  OAI21X1 g129(.A0(n155), .A1(STATO_REG_3__SCAN_IN), .B0(n201), .Y(n205));
  NAND2X1 g130(.A(CONT1_REG_1__SCAN_IN), .B(n95), .Y(n206));
  INVX1   g131(.A(CONT1_REG_1__SCAN_IN), .Y(n207));
  NAND2X1 g132(.A(n207), .B(STATO_REG_3__SCAN_IN), .Y(n208));
  XOR2X1  g133(.A(CONT1_REG_1__SCAN_IN), .B(STATO_REG_3__SCAN_IN), .Y(n209));
  NAND2X1 g134(.A(CONT1_REG_0__SCAN_IN), .B(STATO_REG_3__SCAN_IN), .Y(n210));
  NAND2X1 g135(.A(n210), .B(n209), .Y(n211));
  OAI21X1 g136(.A0(n208), .A1(n206), .B0(n211), .Y(n212));
  INVX1   g137(.A(CONT1_REG_2__SCAN_IN), .Y(n213));
  NOR2X1  g138(.A(n213), .B(n95), .Y(n214));
  INVX1   g139(.A(n214), .Y(n215));
  NOR2X1  g140(.A(n213), .B(STATO_REG_3__SCAN_IN), .Y(n217));
  AOI21X1 g141(.A0(n215), .A1(n212), .B0(n217), .Y(n218));
  NOR4X1  g142(.A(CONT1_REG_3__SCAN_IN), .B(CONT1_REG_4__SCAN_IN), .C(n95), .D(n218), .Y(n219));
  NOR4X1  g143(.A(n160), .B(n188), .C(STATO_REG_3__SCAN_IN), .D(n218), .Y(n220));
  OAI22X1 g144(.A0(n219), .A1(n220), .B0(n191), .B1(n95), .Y(n221));
  OAI21X1 g145(.A0(n191), .A1(STATO_REG_3__SCAN_IN), .B0(n221), .Y(n222));
  AOI21X1 g146(.A0(n222), .A1(n205), .B0(n204), .Y(n223));
  NAND2X1 g147(.A(CONT1_REG_7__SCAN_IN), .B(n95), .Y(n224));
  OAI21X1 g148(.A0(n223), .A1(n201), .B0(n224), .Y(n225));
  XOR2X1  g149(.A(n225), .B(n203), .Y(n226));
  NOR2X1  g150(.A(n96), .B(n185), .Y(n227));
  NOR3X1  g151(.A(n198), .B(n227), .C(n196), .Y(n228));
  NOR3X1  g152(.A(n228), .B(STATO_REG_1__SCAN_IN), .C(n185), .Y(n229));
  XOR2X1  g153(.A(CONT1_REG_8__SCAN_IN), .B(R_IN_REG_1__SCAN_IN), .Y(n230));
  XOR2X1  g154(.A(CONT1_REG_6__SCAN_IN), .B(R_IN_REG_1__SCAN_IN), .Y(n231));
  INVX1   g155(.A(n231), .Y(n232));
  XOR2X1  g156(.A(CONT1_REG_5__SCAN_IN), .B(R_IN_REG_1__SCAN_IN), .Y(n233));
  NOR2X1  g157(.A(n233), .B(n75), .Y(n234));
  XOR2X1  g158(.A(CONT1_REG_4__SCAN_IN), .B(R_IN_REG_1__SCAN_IN), .Y(n235));
  NOR2X1  g159(.A(n235), .B(n79), .Y(n236));
  NAND2X1 g160(.A(n235), .B(n79), .Y(n237));
  XOR2X1  g161(.A(CONT1_REG_3__SCAN_IN), .B(n88), .Y(n238));
  NAND2X1 g162(.A(n238), .B(R_IN_REG_3__SCAN_IN), .Y(n239));
  NOR2X1  g163(.A(n238), .B(R_IN_REG_3__SCAN_IN), .Y(n240));
  XOR2X1  g164(.A(CONT1_REG_2__SCAN_IN), .B(R_IN_REG_1__SCAN_IN), .Y(n241));
  NOR2X1  g165(.A(n241), .B(n85), .Y(n242));
  NAND2X1 g166(.A(n241), .B(n85), .Y(n243));
  XOR2X1  g167(.A(R_IN_REG_1__SCAN_IN), .B(CONT1_REG_0__SCAN_IN), .Y(n244));
  NOR2X1  g168(.A(n244), .B(n91), .Y(n245));
  AOI21X1 g169(.A0(n91), .A1(CONT1_REG_0__SCAN_IN), .B0(R_IN_REG_1__SCAN_IN), .Y(n246));
  OAI22X1 g170(.A0(n245), .A1(n246), .B0(R_IN_REG_1__SCAN_IN), .B1(n207), .Y(n247));
  OAI21X1 g171(.A0(n88), .A1(n207), .B0(n247), .Y(n248));
  AOI21X1 g172(.A0(n248), .A1(n243), .B0(n242), .Y(n249));
  OAI21X1 g173(.A0(n249), .A1(n240), .B0(n239), .Y(n250));
  AOI21X1 g174(.A0(n250), .A1(n237), .B0(n236), .Y(n251));
  AOI21X1 g175(.A0(n233), .A1(n75), .B0(n251), .Y(n252));
  OAI21X1 g176(.A0(n252), .A1(n234), .B0(n232), .Y(n253));
  XOR2X1  g177(.A(CONT1_REG_7__SCAN_IN), .B(R_IN_REG_1__SCAN_IN), .Y(n254));
  NOR2X1  g178(.A(n254), .B(n253), .Y(n255));
  XOR2X1  g179(.A(n255), .B(n230), .Y(n256));
  NOR2X1  g180(.A(n228), .B(STATO_REG_1__SCAN_IN), .Y(n257));
  NAND2X1 g181(.A(n257), .B(n185), .Y(n258));
  AOI21X1 g182(.A0(CONT1_REG_2__SCAN_IN), .A1(CONT1_REG_1__SCAN_IN), .B0(CONT1_REG_3__SCAN_IN), .Y(n259));
  AOI21X1 g183(.A0(n259), .A1(n188), .B0(n191), .Y(n260));
  NAND3X1 g184(.A(n260), .B(CONT1_REG_6__SCAN_IN), .C(CONT1_REG_7__SCAN_IN), .Y(n261));
  XOR2X1  g185(.A(n261), .B(n187), .Y(n262));
  NAND2X1 g186(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .Y(n263));
  NOR3X1  g187(.A(n228), .B(n263), .C(STATO_REG_0__SCAN_IN), .Y(n264));
  AOI22X1 g188(.A0(n262), .A1(n264), .B0(n228), .B1(CONT1_REG_8__SCAN_IN), .Y(n265));
  OAI21X1 g189(.A0(n258), .A1(n256), .B0(n265), .Y(n266));
  AOI21X1 g190(.A0(n229), .A1(n226), .B0(n266), .Y(n267));
  OAI21X1 g191(.A0(n200), .A1(n183), .B0(n267), .Y(U378));
  XOR2X1  g192(.A(CONT1_REG_7__SCAN_IN), .B(R_IN_REG_3__SCAN_IN), .Y(n269));
  INVX1   g193(.A(n199), .Y(n270));
  AOI21X1 g194(.A0(n269), .A1(n180), .B0(n270), .Y(n271));
  OAI21X1 g195(.A0(n269), .A1(n180), .B0(n271), .Y(n272));
  NOR2X1  g196(.A(n154), .B(STATO_REG_3__SCAN_IN), .Y(n273));
  XOR2X1  g197(.A(n273), .B(n201), .Y(n274));
  XOR2X1  g198(.A(n274), .B(n223), .Y(n275));
  NAND2X1 g199(.A(n275), .B(n229), .Y(n276));
  XOR2X1  g200(.A(n254), .B(n253), .Y(n277));
  NAND3X1 g201(.A(n277), .B(n257), .C(n185), .Y(n278));
  NAND2X1 g202(.A(n260), .B(CONT1_REG_6__SCAN_IN), .Y(n279));
  XOR2X1  g203(.A(n279), .B(n154), .Y(n280));
  AOI22X1 g204(.A0(n264), .A1(n280), .B0(n228), .B1(CONT1_REG_7__SCAN_IN), .Y(n281));
  NAND4X1 g205(.A(n278), .B(n276), .C(n272), .D(n281), .Y(U377));
  XOR2X1  g206(.A(CONT1_REG_6__SCAN_IN), .B(R_IN_REG_3__SCAN_IN), .Y(n283));
  AOI21X1 g207(.A0(n177), .A1(n174), .B0(n283), .Y(n284));
  NAND3X1 g208(.A(n283), .B(n177), .C(n174), .Y(n285));
  NAND2X1 g209(.A(n285), .B(n199), .Y(n286));
  NOR3X1  g210(.A(n252), .B(n234), .C(n232), .Y(n287));
  NOR2X1  g211(.A(n287), .B(n258), .Y(n288));
  INVX1   g212(.A(n229), .Y(n289));
  XOR2X1  g213(.A(n204), .B(n201), .Y(n291));
  XOR2X1  g214(.A(n291), .B(n222), .Y(n292));
  NOR4X1  g215(.A(n91), .B(STATO_REG_2__SCAN_IN), .C(n185), .D(n228), .Y(n293));
  INVX1   g216(.A(n228), .Y(n294));
  XOR2X1  g217(.A(n260), .B(CONT1_REG_6__SCAN_IN), .Y(n295));
  NAND2X1 g218(.A(n295), .B(n264), .Y(n296));
  OAI21X1 g219(.A0(n294), .A1(n155), .B0(n296), .Y(n297));
  AOI21X1 g220(.A0(n293), .A1(CONT_REG_5__SCAN_IN), .B0(n297), .Y(n298));
  OAI21X1 g221(.A0(n292), .A1(n289), .B0(n298), .Y(n299));
  AOI21X1 g222(.A0(n288), .A1(n253), .B0(n299), .Y(n300));
  OAI21X1 g223(.A0(n286), .A1(n284), .B0(n300), .Y(U376));
  XOR2X1  g224(.A(n233), .B(R_IN_REG_5__SCAN_IN), .Y(n302));
  XOR2X1  g225(.A(n302), .B(n251), .Y(n303));
  NOR2X1  g226(.A(n228), .B(STATO_REG_2__SCAN_IN), .Y(n304));
  INVX1   g227(.A(n304), .Y(n305));
  NOR2X1  g228(.A(n228), .B(n263), .Y(n306));
  NOR2X1  g229(.A(CONT1_REG_4__SCAN_IN), .B(CONT1_REG_5__SCAN_IN), .Y(n307));
  AOI21X1 g230(.A0(n307), .A1(n259), .B0(n260), .Y(n308));
  NAND2X1 g231(.A(n308), .B(n306), .Y(n309));
  OAI21X1 g232(.A0(n305), .A1(n75), .B0(n309), .Y(n310));
  AOI21X1 g233(.A0(n303), .A1(n257), .B0(n310), .Y(n311));
  XOR2X1  g234(.A(n175), .B(n191), .Y(n312));
  OAI21X1 g235(.A0(n312), .A1(n173), .B0(n199), .Y(n313));
  AOI21X1 g236(.A0(n312), .A1(n173), .B0(n313), .Y(n314));
  NOR2X1  g237(.A(n220), .B(n219), .Y(n315));
  XOR2X1  g238(.A(n191), .B(n315), .Y(n318));
  NOR2X1  g239(.A(n318), .B(n289), .Y(n319));
  NAND2X1 g240(.A(n293), .B(CONT_REG_4__SCAN_IN), .Y(n320));
  NOR4X1  g241(.A(R_IN_REG_0__SCAN_IN), .B(STATO_REG_2__SCAN_IN), .C(n185), .D(n228), .Y(n321));
  AOI22X1 g242(.A0(n228), .A1(CONT1_REG_5__SCAN_IN), .B0(CONT_REG_5__SCAN_IN), .B1(n321), .Y(n322));
  NAND2X1 g243(.A(n322), .B(n320), .Y(n323));
  NOR3X1  g244(.A(n323), .B(n319), .C(n314), .Y(n324));
  OAI21X1 g245(.A0(n311), .A1(STATO_REG_0__SCAN_IN), .B0(n324), .Y(U375));
  INVX1   g246(.A(n257), .Y(n326));
  XOR2X1  g247(.A(n235), .B(R_IN_REG_4__SCAN_IN), .Y(n327));
  XOR2X1  g248(.A(n327), .B(n250), .Y(n328));
  XOR2X1  g249(.A(n259), .B(CONT1_REG_4__SCAN_IN), .Y(n329));
  AOI22X1 g250(.A0(n304), .A1(R_IN_REG_4__SCAN_IN), .B0(n306), .B1(n329), .Y(n330));
  OAI21X1 g251(.A0(n328), .A1(n326), .B0(n330), .Y(n331));
  NAND2X1 g252(.A(n331), .B(n185), .Y(n332));
  XOR2X1  g253(.A(n157), .B(n188), .Y(n333));
  XOR2X1  g254(.A(n333), .B(n172), .Y(n334));
  NAND2X1 g255(.A(n334), .B(n199), .Y(n335));
  AOI22X1 g256(.A0(n228), .A1(CONT1_REG_4__SCAN_IN), .B0(CONT_REG_4__SCAN_IN), .B1(n321), .Y(n336));
  NOR2X1  g257(.A(n160), .B(STATO_REG_3__SCAN_IN), .Y(n337));
  NOR2X1  g258(.A(CONT1_REG_3__SCAN_IN), .B(n95), .Y(n338));
  NAND2X1 g259(.A(n338), .B(n337), .Y(n339));
  XOR2X1  g260(.A(CONT1_REG_3__SCAN_IN), .B(STATO_REG_3__SCAN_IN), .Y(n340));
  INVX1   g261(.A(n340), .Y(n341));
  OAI21X1 g262(.A0(n218), .A1(n341), .B0(n339), .Y(n342));
  XOR2X1  g263(.A(CONT1_REG_4__SCAN_IN), .B(STATO_REG_3__SCAN_IN), .Y(n343));
  XOR2X1  g264(.A(n343), .B(n342), .Y(n344));
  AOI22X1 g265(.A0(n293), .A1(CONT_REG_3__SCAN_IN), .B0(n229), .B1(n344), .Y(n345));
  NAND4X1 g266(.A(n336), .B(n335), .C(n332), .D(n345), .Y(U374));
  XOR2X1  g267(.A(n238), .B(R_IN_REG_3__SCAN_IN), .Y(n347));
  XOR2X1  g268(.A(n347), .B(n249), .Y(n348));
  NAND2X1 g269(.A(CONT1_REG_2__SCAN_IN), .B(CONT1_REG_1__SCAN_IN), .Y(n349));
  XOR2X1  g270(.A(n349), .B(CONT1_REG_3__SCAN_IN), .Y(n350));
  AOI22X1 g271(.A0(n304), .A1(R_IN_REG_3__SCAN_IN), .B0(n306), .B1(n350), .Y(n351));
  OAI21X1 g272(.A0(n348), .A1(n326), .B0(n351), .Y(n352));
  NAND2X1 g273(.A(n352), .B(n185), .Y(n353));
  XOR2X1  g274(.A(n156), .B(n160), .Y(n354));
  OAI21X1 g275(.A0(n354), .A1(n171), .B0(n199), .Y(n355));
  AOI21X1 g276(.A0(n354), .A1(n171), .B0(n355), .Y(n356));
  NOR4X1  g277(.A(n143), .B(n91), .C(n185), .D(n305), .Y(n357));
  NOR4X1  g278(.A(n139), .B(R_IN_REG_0__SCAN_IN), .C(n185), .D(n305), .Y(n358));
  XOR2X1  g279(.A(n340), .B(n218), .Y(n360));
  OAI22X1 g280(.A0(n289), .A1(n360), .B0(n294), .B1(n160), .Y(n361));
  NOR4X1  g281(.A(n358), .B(n357), .C(n356), .D(n361), .Y(n362));
  NAND2X1 g282(.A(n362), .B(n353), .Y(U373));
  XOR2X1  g283(.A(CONT1_REG_2__SCAN_IN), .B(n207), .Y(n364));
  NOR3X1  g284(.A(n364), .B(n228), .C(n263), .Y(n365));
  XOR2X1  g285(.A(n241), .B(R_IN_REG_2__SCAN_IN), .Y(n366));
  XOR2X1  g286(.A(n366), .B(n248), .Y(n367));
  OAI22X1 g287(.A0(n305), .A1(n85), .B0(n326), .B1(n367), .Y(n368));
  OAI21X1 g288(.A0(n368), .A1(n365), .B0(n185), .Y(n369));
  XOR2X1  g289(.A(n213), .B(n211), .Y(n372));
  NOR4X1  g290(.A(n228), .B(STATO_REG_1__SCAN_IN), .C(n185), .D(n372), .Y(n373));
  XOR2X1  g291(.A(n175), .B(CONT1_REG_2__SCAN_IN), .Y(n374));
  XOR2X1  g292(.A(n374), .B(n170), .Y(n375));
  OAI22X1 g293(.A0(n270), .A1(n375), .B0(n294), .B1(n213), .Y(n376));
  NOR2X1  g294(.A(n376), .B(n373), .Y(n377));
  AOI22X1 g295(.A0(n293), .A1(CONT_REG_1__SCAN_IN), .B0(CONT_REG_2__SCAN_IN), .B1(n321), .Y(n378));
  NAND3X1 g296(.A(n378), .B(n377), .C(n369), .Y(U372));
  NOR2X1  g297(.A(n246), .B(n245), .Y(n380));
  XOR2X1  g298(.A(n207), .B(n380), .Y(n383));
  AOI22X1 g299(.A0(n306), .A1(n207), .B0(R_IN_REG_1__SCAN_IN), .B1(n304), .Y(n384));
  OAI21X1 g300(.A0(n383), .A1(n326), .B0(n384), .Y(n385));
  NAND2X1 g301(.A(n385), .B(n185), .Y(n386));
  XOR2X1  g302(.A(n209), .B(n210), .Y(n388));
  NAND2X1 g303(.A(n167), .B(n166), .Y(n389));
  XOR2X1  g304(.A(n157), .B(CONT1_REG_1__SCAN_IN), .Y(n390));
  XOR2X1  g305(.A(n390), .B(n389), .Y(n391));
  OAI22X1 g306(.A0(n270), .A1(n391), .B0(n294), .B1(n207), .Y(n392));
  AOI21X1 g307(.A0(n388), .A1(n229), .B0(n392), .Y(n393));
  AOI22X1 g308(.A0(n293), .A1(CONT_REG_0__SCAN_IN), .B0(CONT_REG_1__SCAN_IN), .B1(n321), .Y(n394));
  NAND3X1 g309(.A(n394), .B(n393), .C(n386), .Y(U371));
  XOR2X1  g310(.A(n244), .B(n91), .Y(n396));
  XOR2X1  g311(.A(n396), .B(R_IN_REG_1__SCAN_IN), .Y(n397));
  AOI22X1 g312(.A0(n306), .A1(CONT1_REG_0__SCAN_IN), .B0(R_IN_REG_0__SCAN_IN), .B1(n304), .Y(n398));
  OAI21X1 g313(.A0(n397), .A1(n326), .B0(n398), .Y(n399));
  NAND2X1 g314(.A(n399), .B(n185), .Y(n400));
  NAND2X1 g315(.A(n321), .B(CONT_REG_0__SCAN_IN), .Y(n401));
  INVX1   g316(.A(CONT1_REG_0__SCAN_IN), .Y(n402));
  NOR2X1  g317(.A(n402), .B(n95), .Y(n403));
  NOR2X1  g318(.A(n402), .B(STATO_REG_3__SCAN_IN), .Y(n404));
  XOR2X1  g319(.A(n404), .B(n403), .Y(n405));
  XOR2X1  g320(.A(R_IN_REG_3__SCAN_IN), .B(CONT1_REG_0__SCAN_IN), .Y(n406));
  XOR2X1  g321(.A(n406), .B(n156), .Y(n407));
  OAI22X1 g322(.A0(n270), .A1(n407), .B0(n294), .B1(n402), .Y(n408));
  AOI21X1 g323(.A0(n405), .A1(n229), .B0(n408), .Y(n409));
  NAND3X1 g324(.A(n409), .B(n401), .C(n400), .Y(U370));
  NAND3X1 g325(.A(n103), .B(CONT1_REG_8__SCAN_IN), .C(STATO_REG_3__SCAN_IN), .Y(n411));
  INVX1   g326(.A(n103), .Y(n412));
  NOR4X1  g327(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .C(STATO_REG_0__SCAN_IN), .D(CONT1_REG_8__SCAN_IN), .Y(n413));
  AOI22X1 g328(.A0(n201), .A1(n413), .B0(n412), .B1(X_OUT_REG_5__SCAN_IN), .Y(n414));
  OAI21X1 g329(.A0(n411), .A1(n318), .B0(n414), .Y(U369));
  NAND4X1 g330(.A(n103), .B(CONT1_REG_8__SCAN_IN), .C(STATO_REG_3__SCAN_IN), .D(n344), .Y(n416));
  NOR3X1  g331(.A(n412), .B(CONT1_REG_8__SCAN_IN), .C(n95), .Y(n417));
  AOI22X1 g332(.A0(n412), .A1(X_OUT_REG_4__SCAN_IN), .B0(CONT1_REG_4__SCAN_IN), .B1(n417), .Y(n418));
  NAND2X1 g333(.A(n418), .B(n416), .Y(U368));
  AOI22X1 g334(.A0(n412), .A1(X_OUT_REG_3__SCAN_IN), .B0(CONT1_REG_3__SCAN_IN), .B1(n417), .Y(n420));
  OAI21X1 g335(.A0(n411), .A1(n360), .B0(n420), .Y(U367));
  AOI22X1 g336(.A0(n214), .A1(n413), .B0(n412), .B1(X_OUT_REG_2__SCAN_IN), .Y(n422));
  OAI21X1 g337(.A0(n411), .A1(n372), .B0(n422), .Y(U366));
  NAND4X1 g338(.A(n103), .B(CONT1_REG_8__SCAN_IN), .C(STATO_REG_3__SCAN_IN), .D(n388), .Y(n424));
  AOI22X1 g339(.A0(n412), .A1(X_OUT_REG_1__SCAN_IN), .B0(CONT1_REG_1__SCAN_IN), .B1(n417), .Y(n425));
  NAND2X1 g340(.A(n425), .B(n424), .Y(U365));
  NAND4X1 g341(.A(CONT1_REG_8__SCAN_IN), .B(CONT1_REG_0__SCAN_IN), .C(STATO_REG_3__SCAN_IN), .D(n103), .Y(n427));
  AOI22X1 g342(.A0(n403), .A1(n413), .B0(n412), .B1(X_OUT_REG_0__SCAN_IN), .Y(n428));
  NAND2X1 g343(.A(n428), .B(n427), .Y(U364));
  NAND2X1 g344(.A(n102), .B(n97), .Y(n430));
  OAI21X1 g345(.A0(n263), .A1(n185), .B0(n430), .Y(U360));
  OAI22X1 g346(.A0(n197), .A1(n185), .B0(n186), .B1(n227), .Y(U361));
  NAND2X1 g347(.A(n110), .B(n97), .Y(n433));
  AOI21X1 g348(.A0(n193), .A1(n96), .B0(n185), .Y(n434));
  NOR3X1  g349(.A(n88), .B(STATO_REG_1__SCAN_IN), .C(STATO_REG_0__SCAN_IN), .Y(n435));
  NOR3X1  g350(.A(n435), .B(n434), .C(n186), .Y(n436));
  NOR4X1  g351(.A(STATO_REG_1__SCAN_IN), .B(n185), .C(STBI), .D(STATO_REG_2__SCAN_IN), .Y(n437));
  NOR2X1  g352(.A(n437), .B(n436), .Y(n438));
  OAI21X1 g353(.A0(n433), .A1(n134), .B0(n438), .Y(U362));
  INVX1   g354(.A(n194), .Y(n440));
  NAND4X1 g355(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .C(n185), .D(n440), .Y(n441));
  AOI21X1 g356(.A0(n88), .A1(n185), .B0(STATO_REG_1__SCAN_IN), .Y(n442));
  OAI21X1 g357(.A0(STATO_REG_2__SCAN_IN), .A1(STBI), .B0(n442), .Y(n443));
  NAND4X1 g358(.A(n441), .B(n433), .C(n412), .D(n443), .Y(U363));
endmodule


