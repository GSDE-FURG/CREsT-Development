// Benchmark "b07_C" written by ABC on Wed Aug 05 14:38:46 2020

module b07_C ( 
    START, STATO_REG_0__SCAN_IN, STATO_REG_1__SCAN_IN,
    PUNTI_RETTA_REG_7__SCAN_IN, PUNTI_RETTA_REG_6__SCAN_IN,
    PUNTI_RETTA_REG_5__SCAN_IN, PUNTI_RETTA_REG_4__SCAN_IN,
    PUNTI_RETTA_REG_3__SCAN_IN, PUNTI_RETTA_REG_2__SCAN_IN,
    PUNTI_RETTA_REG_1__SCAN_IN, PUNTI_RETTA_REG_0__SCAN_IN,
    CONT_REG_7__SCAN_IN, CONT_REG_6__SCAN_IN, CONT_REG_5__SCAN_IN,
    CONT_REG_4__SCAN_IN, CONT_REG_3__SCAN_IN, CONT_REG_2__SCAN_IN,
    CONT_REG_1__SCAN_IN, CONT_REG_0__SCAN_IN, MAR_REG_7__SCAN_IN,
    MAR_REG_6__SCAN_IN, MAR_REG_5__SCAN_IN, MAR_REG_4__SCAN_IN,
    MAR_REG_3__SCAN_IN, MAR_REG_2__SCAN_IN, MAR_REG_1__SCAN_IN,
    MAR_REG_0__SCAN_IN, X_REG_7__SCAN_IN, X_REG_6__SCAN_IN,
    X_REG_5__SCAN_IN, X_REG_4__SCAN_IN, X_REG_3__SCAN_IN, X_REG_2__SCAN_IN,
    X_REG_1__SCAN_IN, X_REG_0__SCAN_IN, Y_REG_3__SCAN_IN, Y_REG_1__SCAN_IN,
    Y_REG_5__SCAN_IN, T_REG_3__SCAN_IN, T_REG_5__SCAN_IN, T_REG_1__SCAN_IN,
    T_REG_0__SCAN_IN, T_REG_4__SCAN_IN, T_REG_6__SCAN_IN, T_REG_2__SCAN_IN,
    Y_REG_4__SCAN_IN, Y_REG_0__SCAN_IN, Y_REG_2__SCAN_IN, Y_REG_6__SCAN_IN,
    STATO_REG_2__SCAN_IN,
    U358, U357, U356, U355, U354, U353, U352, U351, U350, U349, U348, U347,
    U346, U345, U344, U343, U342, U341, U340, U339, U388, U389, U390, U391,
    U338, U337, U336, U335, U334, U333, U332, U331, U330, U394, U329, U395,
    U396, U397, U398, U399, U400, U401, U328, U402, U403, U327, U324, U325,
    U326  );
  input  START, STATO_REG_0__SCAN_IN, STATO_REG_1__SCAN_IN,
    PUNTI_RETTA_REG_7__SCAN_IN, PUNTI_RETTA_REG_6__SCAN_IN,
    PUNTI_RETTA_REG_5__SCAN_IN, PUNTI_RETTA_REG_4__SCAN_IN,
    PUNTI_RETTA_REG_3__SCAN_IN, PUNTI_RETTA_REG_2__SCAN_IN,
    PUNTI_RETTA_REG_1__SCAN_IN, PUNTI_RETTA_REG_0__SCAN_IN,
    CONT_REG_7__SCAN_IN, CONT_REG_6__SCAN_IN, CONT_REG_5__SCAN_IN,
    CONT_REG_4__SCAN_IN, CONT_REG_3__SCAN_IN, CONT_REG_2__SCAN_IN,
    CONT_REG_1__SCAN_IN, CONT_REG_0__SCAN_IN, MAR_REG_7__SCAN_IN,
    MAR_REG_6__SCAN_IN, MAR_REG_5__SCAN_IN, MAR_REG_4__SCAN_IN,
    MAR_REG_3__SCAN_IN, MAR_REG_2__SCAN_IN, MAR_REG_1__SCAN_IN,
    MAR_REG_0__SCAN_IN, X_REG_7__SCAN_IN, X_REG_6__SCAN_IN,
    X_REG_5__SCAN_IN, X_REG_4__SCAN_IN, X_REG_3__SCAN_IN, X_REG_2__SCAN_IN,
    X_REG_1__SCAN_IN, X_REG_0__SCAN_IN, Y_REG_3__SCAN_IN, Y_REG_1__SCAN_IN,
    Y_REG_5__SCAN_IN, T_REG_3__SCAN_IN, T_REG_5__SCAN_IN, T_REG_1__SCAN_IN,
    T_REG_0__SCAN_IN, T_REG_4__SCAN_IN, T_REG_6__SCAN_IN, T_REG_2__SCAN_IN,
    Y_REG_4__SCAN_IN, Y_REG_0__SCAN_IN, Y_REG_2__SCAN_IN, Y_REG_6__SCAN_IN,
    STATO_REG_2__SCAN_IN;
  output U358, U357, U356, U355, U354, U353, U352, U351, U350, U349, U348,
    U347, U346, U345, U344, U343, U342, U341, U340, U339, U388, U389, U390,
    U391, U338, U337, U336, U335, U334, U333, U332, U331, U330, U394, U329,
    U395, U396, U397, U398, U399, U400, U401, U328, U402, U403, U327, U324,
    U325, U326;
  wire n107, n108, n109, n110, n111, n112, n113, n114, n115, n116, n117,
    n118, n119, n120, n121, n122, n123, n124, n125, n126, n127, n128, n129,
    n130, n131, n132, n133, n134, n135, n136, n137, n138, n139, n140, n141,
    n142, n143, n144, n145, n146, n147, n148, n149, n150, n151, n152, n153,
    n154, n155, n156, n157, n158, n159, n160, n161, n162, n163, n164, n165,
    n166, n168, n169, n170, n171, n172, n173, n174, n175, n176, n177, n178,
    n179, n180, n181, n182, n183, n184, n185, n186, n187, n188, n189, n190,
    n191, n192, n193, n194, n195, n196, n197, n198, n199, n201, n202, n203,
    n205, n206, n207, n208, n210, n211, n212, n214, n215, n216, n217, n219,
    n220, n221, n223, n224, n225, n226, n228, n229, n230, n232, n233, n234,
    n235, n238, n240, n243, n246, n248, n250, n251, n252, n254, n256, n259,
    n260, n261, n262, n263, n265, n266, n267, n268, n270, n271, n273, n275,
    n276, n277, n278, n279, n280, n281, n282, n283, n285, n287, n289, n291,
    n294, n295, n296, n297, n298, n300, n301, n302, n304, n305, n306, n308,
    n309, n311, n312, n313, n316, n317, n319, n321, n323, n325, n327, n329,
    n331, n333, n334, n336, n339, n341, n342, n343, n345, n346;
  INVX1   g000(.A(STATO_REG_0__SCAN_IN), .Y(n107));
  NAND3X1 g001(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .C(n107), .Y(n108));
  INVX1   g002(.A(STATO_REG_1__SCAN_IN), .Y(n109));
  INVX1   g003(.A(STATO_REG_2__SCAN_IN), .Y(n110));
  INVX1   g004(.A(MAR_REG_4__SCAN_IN), .Y(n111));
  INVX1   g005(.A(MAR_REG_2__SCAN_IN), .Y(n112));
  INVX1   g006(.A(MAR_REG_1__SCAN_IN), .Y(n113));
  INVX1   g007(.A(MAR_REG_0__SCAN_IN), .Y(n114));
  NOR3X1  g008(.A(n114), .B(n113), .C(n112), .Y(n115));
  NOR3X1  g009(.A(MAR_REG_5__SCAN_IN), .B(MAR_REG_6__SCAN_IN), .C(MAR_REG_7__SCAN_IN), .Y(n116));
  NAND4X1 g010(.A(n115), .B(MAR_REG_3__SCAN_IN), .C(n111), .D(n116), .Y(n117));
  INVX1   g011(.A(n117), .Y(n118));
  NOR2X1  g012(.A(n110), .B(n109), .Y(n119));
  AOI22X1 g013(.A0(n108), .A1(X_REG_6__SCAN_IN), .B0(CONT_REG_6__SCAN_IN), .B1(n119), .Y(n120));
  NOR2X1  g014(.A(STATO_REG_1__SCAN_IN), .B(STATO_REG_0__SCAN_IN), .Y(n121));
  INVX1   g015(.A(X_REG_5__SCAN_IN), .Y(n122));
  INVX1   g016(.A(Y_REG_5__SCAN_IN), .Y(n123));
  NAND2X1 g017(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_0__SCAN_IN), .Y(n124));
  OAI22X1 g018(.A0(STATO_REG_2__SCAN_IN), .A1(n122), .B0(n123), .B1(n124), .Y(n125));
  AOI21X1 g019(.A0(n121), .A1(T_REG_5__SCAN_IN), .B0(n125), .Y(n126));
  AOI22X1 g020(.A0(n108), .A1(X_REG_5__SCAN_IN), .B0(CONT_REG_5__SCAN_IN), .B1(n119), .Y(n127));
  NOR2X1  g021(.A(n127), .B(n126), .Y(n128));
  NAND2X1 g022(.A(n127), .B(n126), .Y(n129));
  NAND2X1 g023(.A(n121), .B(T_REG_4__SCAN_IN), .Y(n130));
  INVX1   g024(.A(n124), .Y(n131));
  AOI22X1 g025(.A0(n110), .A1(X_REG_4__SCAN_IN), .B0(Y_REG_4__SCAN_IN), .B1(n131), .Y(n132));
  NAND2X1 g026(.A(n132), .B(n130), .Y(n133));
  INVX1   g027(.A(CONT_REG_4__SCAN_IN), .Y(n134));
  INVX1   g028(.A(X_REG_4__SCAN_IN), .Y(n135));
  NOR3X1  g029(.A(n110), .B(n109), .C(STATO_REG_0__SCAN_IN), .Y(n136));
  NAND2X1 g030(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .Y(n137));
  OAI22X1 g031(.A0(n136), .A1(n135), .B0(n134), .B1(n137), .Y(n138));
  NAND2X1 g032(.A(n138), .B(n133), .Y(n139));
  NOR2X1  g033(.A(n138), .B(n133), .Y(n140));
  INVX1   g034(.A(X_REG_3__SCAN_IN), .Y(n141));
  INVX1   g035(.A(Y_REG_3__SCAN_IN), .Y(n142));
  OAI22X1 g036(.A0(STATO_REG_2__SCAN_IN), .A1(n141), .B0(n142), .B1(n124), .Y(n143));
  AOI21X1 g037(.A0(n121), .A1(T_REG_3__SCAN_IN), .B0(n143), .Y(n144));
  AOI22X1 g038(.A0(n108), .A1(X_REG_3__SCAN_IN), .B0(CONT_REG_3__SCAN_IN), .B1(n119), .Y(n145));
  NOR2X1  g039(.A(n145), .B(n144), .Y(n146));
  NAND2X1 g040(.A(n145), .B(n144), .Y(n147));
  NAND2X1 g041(.A(n121), .B(T_REG_2__SCAN_IN), .Y(n148));
  AOI22X1 g042(.A0(n110), .A1(X_REG_2__SCAN_IN), .B0(Y_REG_2__SCAN_IN), .B1(n131), .Y(n149));
  NAND2X1 g043(.A(n149), .B(n148), .Y(n150));
  INVX1   g044(.A(CONT_REG_2__SCAN_IN), .Y(n151));
  INVX1   g045(.A(X_REG_2__SCAN_IN), .Y(n152));
  OAI22X1 g046(.A0(n136), .A1(n152), .B0(n151), .B1(n137), .Y(n153));
  NAND2X1 g047(.A(n153), .B(n150), .Y(n154));
  NOR2X1  g048(.A(n153), .B(n150), .Y(n155));
  INVX1   g049(.A(X_REG_1__SCAN_IN), .Y(n156));
  NAND3X1 g050(.A(STATO_REG_2__SCAN_IN), .B(Y_REG_1__SCAN_IN), .C(STATO_REG_0__SCAN_IN), .Y(n157));
  OAI21X1 g051(.A0(STATO_REG_2__SCAN_IN), .A1(n156), .B0(n157), .Y(n158));
  AOI21X1 g052(.A0(n121), .A1(T_REG_1__SCAN_IN), .B0(n158), .Y(n159));
  AOI22X1 g053(.A0(n108), .A1(X_REG_1__SCAN_IN), .B0(CONT_REG_1__SCAN_IN), .B1(n119), .Y(n160));
  NOR2X1  g054(.A(n160), .B(n159), .Y(n161));
  INVX1   g055(.A(Y_REG_0__SCAN_IN), .Y(n162));
  NOR2X1  g056(.A(n124), .B(n162), .Y(n163));
  INVX1   g057(.A(T_REG_0__SCAN_IN), .Y(n164));
  NOR3X1  g058(.A(n164), .B(STATO_REG_1__SCAN_IN), .C(STATO_REG_0__SCAN_IN), .Y(n165));
  INVX1   g059(.A(X_REG_0__SCAN_IN), .Y(n166));
  OAI21X1 g060(.A0(STATO_REG_2__SCAN_IN), .A1(n166), .B0(n137), .Y(n168));
  NOR3X1  g061(.A(n168), .B(n165), .C(n163), .Y(n169));
  AOI22X1 g062(.A0(n108), .A1(X_REG_0__SCAN_IN), .B0(CONT_REG_0__SCAN_IN), .B1(n119), .Y(n170));
  NOR2X1  g063(.A(n170), .B(n169), .Y(n171));
  NAND2X1 g064(.A(n160), .B(n159), .Y(n172));
  AOI21X1 g065(.A0(n172), .A1(n171), .B0(n161), .Y(n173));
  OAI21X1 g066(.A0(n173), .A1(n155), .B0(n154), .Y(n174));
  AOI21X1 g067(.A0(n174), .A1(n147), .B0(n146), .Y(n175));
  OAI21X1 g068(.A0(n175), .A1(n140), .B0(n139), .Y(n176));
  AOI21X1 g069(.A0(n176), .A1(n129), .B0(n128), .Y(n177));
  NOR2X1  g070(.A(n177), .B(n120), .Y(n178));
  INVX1   g071(.A(X_REG_6__SCAN_IN), .Y(n179));
  INVX1   g072(.A(Y_REG_6__SCAN_IN), .Y(n180));
  OAI22X1 g073(.A0(STATO_REG_2__SCAN_IN), .A1(n179), .B0(n180), .B1(n124), .Y(n181));
  AOI21X1 g074(.A0(n121), .A1(T_REG_6__SCAN_IN), .B0(n181), .Y(n182));
  AOI21X1 g075(.A0(n177), .A1(n120), .B0(n182), .Y(n183));
  NOR2X1  g076(.A(n183), .B(n178), .Y(n184));
  INVX1   g077(.A(CONT_REG_7__SCAN_IN), .Y(n185));
  NOR2X1  g078(.A(n137), .B(n185), .Y(n186));
  XOR2X1  g079(.A(n186), .B(n184), .Y(n187));
  INVX1   g080(.A(START), .Y(n188));
  NOR2X1  g081(.A(STATO_REG_2__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .Y(n189));
  INVX1   g082(.A(n189), .Y(n190));
  OAI22X1 g083(.A0(n117), .A1(n137), .B0(n107), .B1(n190), .Y(n191));
  NOR4X1  g084(.A(X_REG_5__SCAN_IN), .B(X_REG_6__SCAN_IN), .C(X_REG_7__SCAN_IN), .D(X_REG_4__SCAN_IN), .Y(n192));
  NOR2X1  g085(.A(X_REG_2__SCAN_IN), .B(X_REG_3__SCAN_IN), .Y(n193));
  NAND4X1 g086(.A(n192), .B(n166), .C(X_REG_1__SCAN_IN), .D(n193), .Y(n194));
  INVX1   g087(.A(n194), .Y(n195));
  NAND4X1 g088(.A(n191), .B(STATO_REG_2__SCAN_IN), .C(n188), .D(n195), .Y(n196));
  NAND2X1 g089(.A(n191), .B(n188), .Y(n197));
  NOR3X1  g090(.A(n195), .B(n197), .C(n110), .Y(n198));
  AOI22X1 g091(.A0(n197), .A1(PUNTI_RETTA_REG_7__SCAN_IN), .B0(CONT_REG_7__SCAN_IN), .B1(n198), .Y(n199));
  OAI21X1 g092(.A0(n196), .A1(n187), .B0(n199), .Y(U358));
  XOR2X1  g093(.A(n182), .B(n120), .Y(n201));
  XOR2X1  g094(.A(n201), .B(n177), .Y(n202));
  AOI22X1 g095(.A0(n197), .A1(PUNTI_RETTA_REG_6__SCAN_IN), .B0(CONT_REG_6__SCAN_IN), .B1(n198), .Y(n203));
  OAI21X1 g096(.A0(n202), .A1(n196), .B0(n203), .Y(U357));
  INVX1   g097(.A(n127), .Y(n205));
  XOR2X1  g098(.A(n205), .B(n126), .Y(n206));
  XOR2X1  g099(.A(n206), .B(n176), .Y(n207));
  AOI22X1 g100(.A0(n197), .A1(PUNTI_RETTA_REG_5__SCAN_IN), .B0(CONT_REG_5__SCAN_IN), .B1(n198), .Y(n208));
  OAI21X1 g101(.A0(n207), .A1(n196), .B0(n208), .Y(U356));
  XOR2X1  g102(.A(n138), .B(n133), .Y(n210));
  XOR2X1  g103(.A(n210), .B(n175), .Y(n211));
  AOI22X1 g104(.A0(n197), .A1(PUNTI_RETTA_REG_4__SCAN_IN), .B0(CONT_REG_4__SCAN_IN), .B1(n198), .Y(n212));
  OAI21X1 g105(.A0(n211), .A1(n196), .B0(n212), .Y(U355));
  INVX1   g106(.A(n145), .Y(n214));
  XOR2X1  g107(.A(n214), .B(n144), .Y(n215));
  XOR2X1  g108(.A(n215), .B(n174), .Y(n216));
  AOI22X1 g109(.A0(n197), .A1(PUNTI_RETTA_REG_3__SCAN_IN), .B0(CONT_REG_3__SCAN_IN), .B1(n198), .Y(n217));
  OAI21X1 g110(.A0(n216), .A1(n196), .B0(n217), .Y(U354));
  XOR2X1  g111(.A(n153), .B(n150), .Y(n219));
  XOR2X1  g112(.A(n219), .B(n173), .Y(n220));
  AOI22X1 g113(.A0(n197), .A1(PUNTI_RETTA_REG_2__SCAN_IN), .B0(CONT_REG_2__SCAN_IN), .B1(n198), .Y(n221));
  OAI21X1 g114(.A0(n220), .A1(n196), .B0(n221), .Y(U353));
  INVX1   g115(.A(n160), .Y(n223));
  XOR2X1  g116(.A(n223), .B(n159), .Y(n224));
  XOR2X1  g117(.A(n224), .B(n171), .Y(n225));
  AOI22X1 g118(.A0(n197), .A1(PUNTI_RETTA_REG_1__SCAN_IN), .B0(CONT_REG_1__SCAN_IN), .B1(n198), .Y(n226));
  OAI21X1 g119(.A0(n225), .A1(n196), .B0(n226), .Y(U352));
  INVX1   g120(.A(n170), .Y(n228));
  XOR2X1  g121(.A(n228), .B(n169), .Y(n229));
  AOI22X1 g122(.A0(n197), .A1(PUNTI_RETTA_REG_0__SCAN_IN), .B0(CONT_REG_0__SCAN_IN), .B1(n198), .Y(n230));
  OAI21X1 g123(.A0(n229), .A1(n196), .B0(n230), .Y(U351));
  NAND4X1 g124(.A(n117), .B(STATO_REG_2__SCAN_IN), .C(STATO_REG_1__SCAN_IN), .D(n195), .Y(n232));
  NAND3X1 g125(.A(n189), .B(STATO_REG_0__SCAN_IN), .C(START), .Y(n233));
  NAND2X1 g126(.A(n233), .B(n232), .Y(n234));
  NOR2X1  g127(.A(n107), .B(n188), .Y(n235));
  OAI22X1 g128(.A0(n234), .A1(n185), .B0(n187), .B1(n232), .Y(U350));
  NAND3X1 g129(.A(n233), .B(n232), .C(CONT_REG_6__SCAN_IN), .Y(n238));
  OAI21X1 g130(.A0(n232), .A1(n202), .B0(n238), .Y(U349));
  NAND3X1 g131(.A(n233), .B(n232), .C(CONT_REG_5__SCAN_IN), .Y(n240));
  OAI21X1 g132(.A0(n232), .A1(n207), .B0(n240), .Y(U348));
  OAI22X1 g133(.A0(n234), .A1(n134), .B0(n211), .B1(n232), .Y(U347));
  NAND3X1 g134(.A(n233), .B(n232), .C(CONT_REG_3__SCAN_IN), .Y(n243));
  OAI21X1 g135(.A0(n232), .A1(n216), .B0(n243), .Y(U346));
  OAI22X1 g136(.A0(n234), .A1(n151), .B0(n220), .B1(n232), .Y(U345));
  NAND3X1 g137(.A(n233), .B(n232), .C(CONT_REG_1__SCAN_IN), .Y(n246));
  OAI21X1 g138(.A0(n232), .A1(n225), .B0(n246), .Y(U344));
  NAND3X1 g139(.A(n233), .B(n232), .C(CONT_REG_0__SCAN_IN), .Y(n248));
  OAI21X1 g140(.A0(n232), .A1(n229), .B0(n248), .Y(U343));
  INVX1   g141(.A(MAR_REG_7__SCAN_IN), .Y(n250));
  AOI22X1 g142(.A0(n110), .A1(n235), .B0(STATO_REG_1__SCAN_IN), .B1(STATO_REG_0__SCAN_IN), .Y(n251));
  OAI21X1 g143(.A0(n118), .A1(n137), .B0(n251), .Y(n252));
  NOR2X1  g144(.A(n252), .B(n250), .Y(U342));
  INVX1   g145(.A(MAR_REG_6__SCAN_IN), .Y(n254));
  NOR2X1  g146(.A(n252), .B(n254), .Y(U341));
  INVX1   g147(.A(MAR_REG_5__SCAN_IN), .Y(n256));
  NOR2X1  g148(.A(n252), .B(n256), .Y(U340));
  NOR2X1  g149(.A(n252), .B(n111), .Y(U339));
  INVX1   g150(.A(MAR_REG_3__SCAN_IN), .Y(n259));
  NAND4X1 g151(.A(n190), .B(n115), .C(n259), .D(n252), .Y(n260));
  INVX1   g152(.A(n252), .Y(n261));
  NOR2X1  g153(.A(n189), .B(n115), .Y(n262));
  OAI21X1 g154(.A0(n262), .A1(n261), .B0(MAR_REG_3__SCAN_IN), .Y(n263));
  NAND2X1 g155(.A(n263), .B(n260), .Y(U388));
  NOR3X1  g156(.A(n114), .B(n113), .C(MAR_REG_2__SCAN_IN), .Y(n265));
  NAND3X1 g157(.A(n265), .B(n252), .C(n190), .Y(n266));
  AOI21X1 g158(.A0(MAR_REG_0__SCAN_IN), .A1(MAR_REG_1__SCAN_IN), .B0(n189), .Y(n267));
  OAI21X1 g159(.A0(n267), .A1(n261), .B0(MAR_REG_2__SCAN_IN), .Y(n268));
  NAND2X1 g160(.A(n268), .B(n266), .Y(U389));
  NAND4X1 g161(.A(n190), .B(MAR_REG_0__SCAN_IN), .C(n113), .D(n252), .Y(n270));
  AOI21X1 g162(.A0(n190), .A1(n114), .B0(n261), .Y(n271));
  OAI21X1 g163(.A0(n271), .A1(n113), .B0(n270), .Y(U390));
  NAND3X1 g164(.A(n252), .B(n190), .C(n114), .Y(n273));
  OAI21X1 g165(.A0(n252), .A1(n114), .B0(n273), .Y(U391));
  NAND2X1 g166(.A(n110), .B(n107), .Y(n275));
  NAND3X1 g167(.A(n275), .B(STATO_REG_2__SCAN_IN), .C(n109), .Y(n276));
  NOR3X1  g168(.A(STATO_REG_2__SCAN_IN), .B(n109), .C(STATO_REG_0__SCAN_IN), .Y(n277));
  AOI21X1 g169(.A0(STATO_REG_2__SCAN_IN), .A1(n109), .B0(n277), .Y(n278));
  NOR4X1  g170(.A(n113), .B(MAR_REG_2__SCAN_IN), .C(n259), .D(MAR_REG_0__SCAN_IN), .Y(n279));
  NOR4X1  g171(.A(MAR_REG_1__SCAN_IN), .B(MAR_REG_2__SCAN_IN), .C(MAR_REG_3__SCAN_IN), .D(n114), .Y(n280));
  NOR2X1  g172(.A(n280), .B(n279), .Y(n281));
  NOR3X1  g173(.A(n281), .B(n278), .C(STATO_REG_2__SCAN_IN), .Y(n282));
  AOI21X1 g174(.A0(n278), .A1(X_REG_7__SCAN_IN), .B0(n282), .Y(n283));
  OAI21X1 g175(.A0(n276), .A1(n187), .B0(n283), .Y(U338));
  AOI21X1 g176(.A0(n278), .A1(X_REG_6__SCAN_IN), .B0(n282), .Y(n285));
  OAI21X1 g177(.A0(n276), .A1(n202), .B0(n285), .Y(U337));
  AOI21X1 g178(.A0(n278), .A1(X_REG_5__SCAN_IN), .B0(n282), .Y(n287));
  OAI21X1 g179(.A0(n276), .A1(n207), .B0(n287), .Y(U336));
  AOI21X1 g180(.A0(n278), .A1(X_REG_4__SCAN_IN), .B0(n282), .Y(n289));
  OAI21X1 g181(.A0(n276), .A1(n211), .B0(n289), .Y(U335));
  AOI21X1 g182(.A0(n278), .A1(X_REG_3__SCAN_IN), .B0(n282), .Y(n291));
  OAI21X1 g183(.A0(n276), .A1(n216), .B0(n291), .Y(U334));
  NOR2X1  g184(.A(MAR_REG_2__SCAN_IN), .B(n259), .Y(n294));
  NOR3X1  g185(.A(MAR_REG_1__SCAN_IN), .B(MAR_REG_2__SCAN_IN), .C(MAR_REG_3__SCAN_IN), .Y(n295));
  AOI22X1 g186(.A0(n294), .A1(MAR_REG_1__SCAN_IN), .B0(MAR_REG_0__SCAN_IN), .B1(n295), .Y(n296));
  INVX1   g187(.A(n296), .Y(n297));
  AOI22X1 g188(.A0(n277), .A1(n297), .B0(n278), .B1(X_REG_2__SCAN_IN), .Y(n298));
  OAI21X1 g189(.A0(n276), .A1(n220), .B0(n298), .Y(U333));
  AOI22X1 g190(.A0(MAR_REG_0__SCAN_IN), .A1(n113), .B0(MAR_REG_3__SCAN_IN), .B1(n115), .Y(n300));
  NAND2X1 g191(.A(n300), .B(n281), .Y(n301));
  AOI22X1 g192(.A0(n277), .A1(n301), .B0(n278), .B1(X_REG_1__SCAN_IN), .Y(n302));
  OAI21X1 g193(.A0(n276), .A1(n225), .B0(n302), .Y(U332));
  NOR2X1  g194(.A(n297), .B(n295), .Y(n304));
  NOR3X1  g195(.A(n304), .B(n278), .C(STATO_REG_2__SCAN_IN), .Y(n305));
  AOI21X1 g196(.A0(n278), .A1(X_REG_0__SCAN_IN), .B0(n305), .Y(n306));
  OAI21X1 g197(.A0(n276), .A1(n229), .B0(n306), .Y(U331));
  NOR3X1  g198(.A(n110), .B(STATO_REG_1__SCAN_IN), .C(STATO_REG_0__SCAN_IN), .Y(n308));
  OAI21X1 g199(.A0(n280), .A1(n279), .B0(n308), .Y(n309));
  OAI21X1 g200(.A0(n308), .A1(n142), .B0(n309), .Y(U330));
  INVX1   g201(.A(n121), .Y(n311));
  OAI21X1 g202(.A0(n311), .A1(n110), .B0(Y_REG_1__SCAN_IN), .Y(n312));
  NAND2X1 g203(.A(n308), .B(n301), .Y(n313));
  NAND2X1 g204(.A(n313), .B(n312), .Y(U394));
  OAI21X1 g205(.A0(n308), .A1(n123), .B0(n309), .Y(U329));
  NAND2X1 g206(.A(STATO_REG_1__SCAN_IN), .B(STATO_REG_0__SCAN_IN), .Y(n316));
  NAND2X1 g207(.A(n316), .B(T_REG_3__SCAN_IN), .Y(n317));
  OAI21X1 g208(.A0(n316), .A1(n216), .B0(n317), .Y(U395));
  NAND2X1 g209(.A(n316), .B(T_REG_5__SCAN_IN), .Y(n319));
  OAI21X1 g210(.A0(n316), .A1(n207), .B0(n319), .Y(U396));
  NAND2X1 g211(.A(n316), .B(T_REG_1__SCAN_IN), .Y(n321));
  OAI21X1 g212(.A0(n316), .A1(n225), .B0(n321), .Y(U397));
  NAND2X1 g213(.A(n316), .B(T_REG_0__SCAN_IN), .Y(n323));
  OAI21X1 g214(.A0(n316), .A1(n229), .B0(n323), .Y(U398));
  NAND2X1 g215(.A(n316), .B(T_REG_4__SCAN_IN), .Y(n325));
  OAI21X1 g216(.A0(n316), .A1(n211), .B0(n325), .Y(U399));
  NAND2X1 g217(.A(n316), .B(T_REG_6__SCAN_IN), .Y(n327));
  OAI21X1 g218(.A0(n316), .A1(n202), .B0(n327), .Y(U400));
  NAND2X1 g219(.A(n316), .B(T_REG_2__SCAN_IN), .Y(n329));
  OAI21X1 g220(.A0(n316), .A1(n220), .B0(n329), .Y(U401));
  OAI21X1 g221(.A0(n311), .A1(n110), .B0(Y_REG_4__SCAN_IN), .Y(n331));
  NAND2X1 g222(.A(n331), .B(n309), .Y(U328));
  INVX1   g223(.A(n308), .Y(n333));
  OAI21X1 g224(.A0(n311), .A1(n110), .B0(Y_REG_0__SCAN_IN), .Y(n334));
  OAI21X1 g225(.A0(n333), .A1(n304), .B0(n334), .Y(U402));
  OAI21X1 g226(.A0(n311), .A1(n110), .B0(Y_REG_2__SCAN_IN), .Y(n336));
  OAI21X1 g227(.A0(n333), .A1(n296), .B0(n336), .Y(U403));
  OAI21X1 g228(.A0(n308), .A1(n180), .B0(n309), .Y(U327));
  AOI21X1 g229(.A0(n118), .A1(START), .B0(n109), .Y(n339));
  OAI21X1 g230(.A0(n339), .A1(n110), .B0(n316), .Y(U324));
  NAND2X1 g231(.A(n118), .B(n188), .Y(n341));
  OAI21X1 g232(.A0(n341), .A1(n110), .B0(n107), .Y(n342));
  AOI21X1 g233(.A0(n235), .A1(n109), .B0(n131), .Y(n343));
  OAI21X1 g234(.A0(n342), .A1(n109), .B0(n343), .Y(U325));
  OAI21X1 g235(.A0(n117), .A1(n137), .B0(n190), .Y(n345));
  NAND2X1 g236(.A(n345), .B(n188), .Y(n346));
  NAND3X1 g237(.A(n346), .B(n275), .C(n311), .Y(U326));
endmodule


