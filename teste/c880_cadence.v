// Benchmark "c880.blif" written by ABC on Fri Sep  1 22:13:28 2017

module \c880.blif  ( 
    G1gat, G8gat, G13gat, G17gat, G26gat, G29gat, G36gat, G42gat, G51gat,
    G55gat, G59gat, G68gat, G72gat, G73gat, G74gat, G75gat, G80gat, G85gat,
    G86gat, G87gat, G88gat, G89gat, G90gat, G91gat, G96gat, G101gat,
    G106gat, G111gat, G116gat, G121gat, G126gat, G130gat, G135gat, G138gat,
    G143gat, G146gat, G149gat, G152gat, G153gat, G156gat, G159gat, G165gat,
    G171gat, G177gat, G183gat, G189gat, G195gat, G201gat, G207gat, G210gat,
    G219gat, G228gat, G237gat, G246gat, G255gat, G259gat, G260gat, G261gat,
    G267gat, G268gat,
    G388gat, G389gat, G390gat, G391gat, G418gat, G419gat, G420gat, G421gat,
    G422gat, G423gat, G446gat, G447gat, G448gat, G449gat, G450gat, G767gat,
    G768gat, G850gat, G863gat, G864gat, G865gat, G866gat, G874gat, G878gat,
    G879gat, G880gat  );
  input  G1gat, G8gat, G13gat, G17gat, G26gat, G29gat, G36gat, G42gat,
    G51gat, G55gat, G59gat, G68gat, G72gat, G73gat, G74gat, G75gat, G80gat,
    G85gat, G86gat, G87gat, G88gat, G89gat, G90gat, G91gat, G96gat,
    G101gat, G106gat, G111gat, G116gat, G121gat, G126gat, G130gat, G135gat,
    G138gat, G143gat, G146gat, G149gat, G152gat, G153gat, G156gat, G159gat,
    G165gat, G171gat, G177gat, G183gat, G189gat, G195gat, G201gat, G207gat,
    G210gat, G219gat, G228gat, G237gat, G246gat, G255gat, G259gat, G260gat,
    G261gat, G267gat, G268gat;
  output G388gat, G389gat, G390gat, G391gat, G418gat, G419gat, G420gat,
    G421gat, G422gat, G423gat, G446gat, G447gat, G448gat, G449gat, G450gat,
    G767gat, G768gat, G850gat, G863gat, G864gat, G865gat, G866gat, G874gat,
    G878gat, G879gat, G880gat;
  wire n87, n89, n93, n94, n96, n101, n104, n106, n107, n109, n112, n113,
    n114, n115, n116, n117, n118, n119, n121, n122, n123, n124, n125, n126,
    n127, n128, n130, n131, n132, n133, n134, n135, n136, n137, n138, n139,
    n140, n141, n142, n143, n144, n145, n146, n147, n148, n149, n150, n151,
    n152, n153, n154, n155, n156, n157, n158, n159, n160, n161, n162, n163,
    n164, n166, n167, n168, n169, n170, n171, n172, n173, n174, n175, n176,
    n177, n178, n179, n180, n181, n182, n183, n184, n185, n186, n187, n188,
    n189, n190, n191, n192, n193, n194, n195, n196, n197, n198, n199, n200,
    n201, n202, n203, n204, n205, n206, n207, n209, n210, n211, n212, n213,
    n214, n215, n216, n217, n218, n219, n220, n221, n222, n223, n225, n226,
    n227, n228, n229, n230, n231, n232, n233, n234, n236, n237, n238, n239,
    n240, n241, n242, n243, n244, n245, n246, n247, n248, n249, n250, n251,
    n252, n253, n254, n255, n256, n257, n258, n259, n260, n261, n262, n263,
    n264, n265, n266, n267, n268, n269, n270, n271, n273, n274, n275, n276,
    n277, n278, n279, n280, n282, n283, n284, n285, n286, n287, n288, n289,
    n290, n292, n293, n294, n295, n296, n297, n298, n299, n300, n301, n302,
    n303, n305, n306, n307, n308, n309, n310, n311, n312, n313, n314;
  AND2X1   g000(.A(G75gat), .B(G29gat), .Y(n87));
  AND2X1   g001(.A(n87), .B(G42gat), .Y(G388gat));
  AND2X1   g002(.A(G36gat), .B(G29gat), .Y(n89));
  AND2X1   g003(.A(n89), .B(G80gat), .Y(G389gat));
  AND2X1   g004(.A(n89), .B(G42gat), .Y(G390gat));
  AND2X1   g005(.A(G86gat), .B(G85gat), .Y(G391gat));
  AND2X1   g006(.A(G17gat), .B(G13gat), .Y(n93));
  AND2X1   g007(.A(G8gat), .B(G1gat), .Y(n94));
  AND2X1   g008(.A(n94), .B(n93), .Y(G418gat));
  NAND4X1  g009(.A(G26gat), .B(G17gat), .C(G13gat), .D(G1gat), .Y(n96));
  OR2X1    g010(.A(n96), .B(G390gat), .Y(G419gat));
  NAND3X1  g011(.A(G80gat), .B(G75gat), .C(G59gat), .Y(G420gat));
  NAND3X1  g012(.A(G80gat), .B(G59gat), .C(G36gat), .Y(G421gat));
  NAND3X1  g013(.A(G59gat), .B(G42gat), .C(G36gat), .Y(G422gat));
  OR2X1    g014(.A(G88gat), .B(G87gat), .Y(n101));
  AND2X1   g015(.A(n101), .B(G90gat), .Y(G423gat));
  NAND4X1  g016(.A(n93), .B(G390gat), .C(G26gat), .D(G1gat), .Y(G446gat));
  NAND3X1  g017(.A(G51gat), .B(G26gat), .C(G1gat), .Y(n104));
  INVX1    g018(.A(n104), .Y(G447gat));
  NAND4X1  g019(.A(G55gat), .B(G13gat), .C(G8gat), .D(G1gat), .Y(n106));
  NAND2X1  g020(.A(G68gat), .B(G29gat), .Y(n107));
  NOR2X1   g021(.A(n107), .B(n106), .Y(G448gat));
  NAND3X1  g022(.A(G74gat), .B(G68gat), .C(G59gat), .Y(n109));
  NOR2X1   g023(.A(n109), .B(n106), .Y(G449gat));
  AND2X1   g024(.A(n101), .B(G89gat), .Y(G450gat));
  XOR2X1   g025(.A(G96gat), .B(G91gat), .Y(n112));
  XOR2X1   g026(.A(G106gat), .B(G101gat), .Y(n113));
  XOR2X1   g027(.A(n113), .B(n112), .Y(n114));
  XOR2X1   g028(.A(n114), .B(G130gat), .Y(n115));
  XOR2X1   g029(.A(G116gat), .B(G111gat), .Y(n116));
  XOR2X1   g030(.A(G126gat), .B(G121gat), .Y(n117));
  XOR2X1   g031(.A(n117), .B(n116), .Y(n118));
  XOR2X1   g032(.A(n118), .B(G135gat), .Y(n119));
  XOR2X1   g033(.A(n119), .B(n115), .Y(G767gat));
  XOR2X1   g034(.A(G165gat), .B(G159gat), .Y(n121));
  XOR2X1   g035(.A(G177gat), .B(G171gat), .Y(n122));
  XOR2X1   g036(.A(n122), .B(n121), .Y(n123));
  XOR2X1   g037(.A(n123), .B(G130gat), .Y(n124));
  XOR2X1   g038(.A(G189gat), .B(G183gat), .Y(n125));
  XOR2X1   g039(.A(G201gat), .B(G195gat), .Y(n126));
  XOR2X1   g040(.A(n126), .B(n125), .Y(n127));
  XOR2X1   g041(.A(n127), .B(G207gat), .Y(n128));
  XOR2X1   g042(.A(n128), .B(n124), .Y(G768gat));
  INVX1    g043(.A(G201gat), .Y(n130));
  INVX1    g044(.A(G126gat), .Y(n131));
  AND2X1   g045(.A(G42gat), .B(G17gat), .Y(n132));
  NAND2X1  g046(.A(G156gat), .B(G59gat), .Y(n133));
  NOR2X1   g047(.A(G42gat), .B(G17gat), .Y(n134));
  OR4X1    g048(.A(n134), .B(n133), .C(n132), .D(n104), .Y(n135));
  NAND3X1  g049(.A(G75gat), .B(G59gat), .C(G42gat), .Y(n136));
  NAND4X1  g050(.A(n136), .B(n94), .C(G51gat), .D(G17gat), .Y(n137));
  AOI21X1  g051(.A0(n137), .A1(n135), .B0(n131), .Y(n138));
  INVX1    g052(.A(G55gat), .Y(n139));
  NAND3X1  g053(.A(G80gat), .B(G75gat), .C(G29gat), .Y(n140));
  NOR4X1   g054(.A(n140), .B(n104), .C(G268gat), .D(n139), .Y(n141));
  INVX1    g055(.A(G153gat), .Y(n142));
  INVX1    g056(.A(G17gat), .Y(n143));
  INVX1    g057(.A(G51gat), .Y(n144));
  NAND2X1  g058(.A(G26gat), .B(G1gat), .Y(n145));
  AND2X1   g059(.A(G156gat), .B(G59gat), .Y(n146));
  OR4X1    g060(.A(n146), .B(n145), .C(n144), .D(n143), .Y(n147));
  AOI21X1  g061(.A0(n147), .A1(G1gat), .B0(n142), .Y(n148));
  NOR3X1   g062(.A(n148), .B(n141), .C(n138), .Y(n149));
  XOR2X1   g063(.A(n149), .B(n130), .Y(n150));
  AND2X1   g064(.A(n150), .B(G261gat), .Y(n151));
  OAI21X1  g065(.A0(n150), .A1(G261gat), .B0(G219gat), .Y(n152));
  AND2X1   g066(.A(n150), .B(G228gat), .Y(n153));
  INVX1    g067(.A(G237gat), .Y(n154));
  NOR3X1   g068(.A(n149), .B(n154), .C(n130), .Y(n155));
  INVX1    g069(.A(G246gat), .Y(n156));
  NOR2X1   g070(.A(n149), .B(n156), .Y(n157));
  INVX1    g071(.A(G42gat), .Y(n158));
  INVX1    g072(.A(G59gat), .Y(n159));
  NAND3X1  g073(.A(G73gat), .B(G72gat), .C(G68gat), .Y(n160));
  OR4X1    g074(.A(n160), .B(n106), .C(n159), .D(n158), .Y(n161));
  AOI22X1  g075(.A0(G267gat), .A1(G255gat), .B0(G210gat), .B1(G121gat), .Y(n162));
  OAI21X1  g076(.A0(n161), .A1(n130), .B0(n162), .Y(n163));
  NOR4X1   g077(.A(n163), .B(n157), .C(n155), .D(n153), .Y(n164));
  OAI21X1  g078(.A0(n152), .A1(n151), .B0(n164), .Y(G850gat));
  OR4X1    g079(.A(n140), .B(n104), .C(G268gat), .D(n139), .Y(n166));
  NOR4X1   g080(.A(n134), .B(n133), .C(n132), .D(n104), .Y(n167));
  AND2X1   g081(.A(G75gat), .B(G59gat), .Y(n168));
  NAND4X1  g082(.A(G51gat), .B(G17gat), .C(G8gat), .D(G1gat), .Y(n169));
  AOI21X1  g083(.A0(n168), .A1(G42gat), .B0(n169), .Y(n170));
  OAI21X1  g084(.A0(n170), .A1(n167), .B0(G111gat), .Y(n171));
  INVX1    g085(.A(G1gat), .Y(n172));
  NOR3X1   g086(.A(n146), .B(n104), .C(n143), .Y(n173));
  OAI21X1  g087(.A0(n173), .A1(n172), .B0(G143gat), .Y(n174));
  NAND3X1  g088(.A(n174), .B(n171), .C(n166), .Y(n175));
  XOR2X1   g089(.A(n175), .B(G183gat), .Y(n176));
  INVX1    g090(.A(G261gat), .Y(n177));
  NOR4X1   g091(.A(n148), .B(n141), .C(n138), .D(G201gat), .Y(n178));
  INVX1    g092(.A(G116gat), .Y(n179));
  AOI21X1  g093(.A0(n137), .A1(n135), .B0(n179), .Y(n180));
  INVX1    g094(.A(G146gat), .Y(n181));
  AOI21X1  g095(.A0(n147), .A1(G1gat), .B0(n181), .Y(n182));
  NOR4X1   g096(.A(n182), .B(n180), .C(n141), .D(G189gat), .Y(n183));
  INVX1    g097(.A(G121gat), .Y(n184));
  AOI21X1  g098(.A0(n137), .A1(n135), .B0(n184), .Y(n185));
  INVX1    g099(.A(G149gat), .Y(n186));
  AOI21X1  g100(.A0(n147), .A1(G1gat), .B0(n186), .Y(n187));
  NOR4X1   g101(.A(n187), .B(n185), .C(n141), .D(G195gat), .Y(n188));
  NOR4X1   g102(.A(n188), .B(n183), .C(n178), .D(n177), .Y(n189));
  NOR4X1   g103(.A(n188), .B(n183), .C(n149), .D(n130), .Y(n190));
  OAI21X1  g104(.A0(n170), .A1(n167), .B0(G116gat), .Y(n191));
  OAI21X1  g105(.A0(n173), .A1(n172), .B0(G146gat), .Y(n192));
  NAND3X1  g106(.A(n192), .B(n191), .C(n166), .Y(n193));
  AND2X1   g107(.A(n193), .B(G189gat), .Y(n194));
  INVX1    g108(.A(G195gat), .Y(n195));
  NOR3X1   g109(.A(n187), .B(n185), .C(n141), .Y(n196));
  NOR3X1   g110(.A(n196), .B(n183), .C(n195), .Y(n197));
  NOR4X1   g111(.A(n197), .B(n194), .C(n190), .D(n189), .Y(n198));
  INVX1    g112(.A(n198), .Y(n199));
  AND2X1   g113(.A(n199), .B(n176), .Y(n200));
  OAI21X1  g114(.A0(n199), .A1(n176), .B0(G219gat), .Y(n201));
  NAND3X1  g115(.A(n175), .B(G237gat), .C(G183gat), .Y(n202));
  NAND2X1  g116(.A(n175), .B(G246gat), .Y(n203));
  INVX1    g117(.A(n161), .Y(n204));
  AOI22X1  g118(.A0(n204), .A1(G183gat), .B0(G210gat), .B1(G106gat), .Y(n205));
  NAND3X1  g119(.A(n205), .B(n203), .C(n202), .Y(n206));
  AOI21X1  g120(.A0(n176), .A1(G228gat), .B0(n206), .Y(n207));
  OAI21X1  g121(.A0(n201), .A1(n200), .B0(n207), .Y(G863gat));
  XOR2X1   g122(.A(n193), .B(G189gat), .Y(n209));
  NOR3X1   g123(.A(n188), .B(n178), .C(n177), .Y(n210));
  OR2X1    g124(.A(n149), .B(n130), .Y(n211));
  OR2X1    g125(.A(n196), .B(n195), .Y(n212));
  OAI21X1  g126(.A0(n188), .A1(n211), .B0(n212), .Y(n213));
  OR2X1    g127(.A(n213), .B(n210), .Y(n214));
  AND2X1   g128(.A(n214), .B(n209), .Y(n215));
  OAI21X1  g129(.A0(n214), .A1(n209), .B0(G219gat), .Y(n216));
  NAND3X1  g130(.A(n193), .B(G237gat), .C(G189gat), .Y(n217));
  INVX1    g131(.A(G189gat), .Y(n218));
  AOI22X1  g132(.A0(G259gat), .A1(G255gat), .B0(G210gat), .B1(G111gat), .Y(n219));
  OAI21X1  g133(.A0(n161), .A1(n218), .B0(n219), .Y(n220));
  AOI21X1  g134(.A0(n193), .A1(G246gat), .B0(n220), .Y(n221));
  NAND2X1  g135(.A(n221), .B(n217), .Y(n222));
  AOI21X1  g136(.A0(n209), .A1(G228gat), .B0(n222), .Y(n223));
  OAI21X1  g137(.A0(n216), .A1(n215), .B0(n223), .Y(G864gat));
  XOR2X1   g138(.A(n196), .B(n195), .Y(n225));
  OAI21X1  g139(.A0(n178), .A1(n177), .B0(n211), .Y(n226));
  AND2X1   g140(.A(n226), .B(n225), .Y(n227));
  OAI21X1  g141(.A0(n226), .A1(n225), .B0(G219gat), .Y(n228));
  AND2X1   g142(.A(n225), .B(G228gat), .Y(n229));
  NOR3X1   g143(.A(n196), .B(n154), .C(n195), .Y(n230));
  NOR2X1   g144(.A(n196), .B(n156), .Y(n231));
  AOI22X1  g145(.A0(G260gat), .A1(G255gat), .B0(G210gat), .B1(G116gat), .Y(n232));
  OAI21X1  g146(.A0(n161), .A1(n195), .B0(n232), .Y(n233));
  NOR4X1   g147(.A(n233), .B(n231), .C(n230), .D(n229), .Y(n234));
  OAI21X1  g148(.A0(n228), .A1(n227), .B0(n234), .Y(G865gat));
  INVX1    g149(.A(G159gat), .Y(n236));
  NAND2X1  g150(.A(n137), .B(n135), .Y(n237));
  NAND4X1  g151(.A(n133), .B(G447gat), .C(G143gat), .D(G55gat), .Y(n238));
  NAND2X1  g152(.A(G138gat), .B(G8gat), .Y(n239));
  OR4X1    g153(.A(n140), .B(n104), .C(G268gat), .D(n143), .Y(n240));
  NAND3X1  g154(.A(n240), .B(n239), .C(n238), .Y(n241));
  AOI21X1  g155(.A0(n237), .A1(G91gat), .B0(n241), .Y(n242));
  NOR2X1   g156(.A(n242), .B(n236), .Y(n243));
  INVX1    g157(.A(G165gat), .Y(n244));
  INVX1    g158(.A(G171gat), .Y(n245));
  OR4X1    g159(.A(n146), .B(n104), .C(n181), .D(n139), .Y(n246));
  NAND2X1  g160(.A(G138gat), .B(G51gat), .Y(n247));
  NAND3X1  g161(.A(n247), .B(n246), .C(n240), .Y(n248));
  AOI21X1  g162(.A0(n237), .A1(G96gat), .B0(n248), .Y(n249));
  OR4X1    g163(.A(n146), .B(n104), .C(n186), .D(n139), .Y(n250));
  NAND2X1  g164(.A(G138gat), .B(G17gat), .Y(n251));
  NAND3X1  g165(.A(n251), .B(n250), .C(n240), .Y(n252));
  AOI21X1  g166(.A0(n237), .A1(G101gat), .B0(n252), .Y(n253));
  AOI22X1  g167(.A0(n253), .A1(n245), .B0(n249), .B1(n244), .Y(n254));
  INVX1    g168(.A(G177gat), .Y(n255));
  OR4X1    g169(.A(n146), .B(n104), .C(n142), .D(n139), .Y(n256));
  NAND2X1  g170(.A(G152gat), .B(G138gat), .Y(n257));
  NAND3X1  g171(.A(n257), .B(n256), .C(n240), .Y(n258));
  AOI21X1  g172(.A0(n237), .A1(G106gat), .B0(n258), .Y(n259));
  NAND2X1  g173(.A(n259), .B(n255), .Y(n260));
  NOR2X1   g174(.A(n175), .B(G183gat), .Y(n261));
  NAND2X1  g175(.A(n175), .B(G183gat), .Y(n262));
  OAI21X1  g176(.A0(n198), .A1(n261), .B0(n262), .Y(n263));
  NAND3X1  g177(.A(n263), .B(n260), .C(n254), .Y(n264));
  NOR2X1   g178(.A(n259), .B(n255), .Y(n265));
  AND2X1   g179(.A(n249), .B(n244), .Y(n266));
  OR2X1    g180(.A(n249), .B(n244), .Y(n267));
  OR2X1    g181(.A(n253), .B(n245), .Y(n268));
  OAI21X1  g182(.A0(n268), .A1(n266), .B0(n267), .Y(n269));
  AOI21X1  g183(.A0(n265), .A1(n254), .B0(n269), .Y(n270));
  AOI22X1  g184(.A0(n270), .A1(n264), .B0(n242), .B1(n236), .Y(n271));
  OR2X1    g185(.A(n271), .B(n243), .Y(G866gat));
  XOR2X1   g186(.A(n259), .B(n255), .Y(n273));
  AND2X1   g187(.A(n273), .B(n263), .Y(n274));
  OAI21X1  g188(.A0(n273), .A1(n263), .B0(G219gat), .Y(n275));
  AND2X1   g189(.A(n273), .B(G228gat), .Y(n276));
  NOR3X1   g190(.A(n259), .B(n154), .C(n255), .Y(n277));
  AOI22X1  g191(.A0(n204), .A1(G177gat), .B0(G210gat), .B1(G101gat), .Y(n278));
  OAI21X1  g192(.A0(n259), .A1(n156), .B0(n278), .Y(n279));
  NOR3X1   g193(.A(n279), .B(n277), .C(n276), .Y(n280));
  OAI21X1  g194(.A0(n275), .A1(n274), .B0(n280), .Y(G874gat));
  NAND2X1  g195(.A(n270), .B(n264), .Y(n282));
  XOR2X1   g196(.A(n242), .B(n236), .Y(n283));
  AND2X1   g197(.A(n283), .B(n282), .Y(n284));
  OAI21X1  g198(.A0(n283), .A1(n282), .B0(G219gat), .Y(n285));
  AND2X1   g199(.A(n283), .B(G228gat), .Y(n286));
  NOR3X1   g200(.A(n242), .B(n154), .C(n236), .Y(n287));
  AOI22X1  g201(.A0(n204), .A1(G159gat), .B0(G268gat), .B1(G210gat), .Y(n288));
  OAI21X1  g202(.A0(n242), .A1(n156), .B0(n288), .Y(n289));
  NOR3X1   g203(.A(n289), .B(n287), .C(n286), .Y(n290));
  OAI21X1  g204(.A0(n285), .A1(n284), .B0(n290), .Y(G878gat));
  XOR2X1   g205(.A(n249), .B(n244), .Y(n292));
  NAND2X1  g206(.A(n253), .B(n245), .Y(n293));
  NAND3X1  g207(.A(n263), .B(n260), .C(n293), .Y(n294));
  NAND2X1  g208(.A(n265), .B(n293), .Y(n295));
  NAND3X1  g209(.A(n295), .B(n294), .C(n268), .Y(n296));
  AND2X1   g210(.A(n296), .B(n292), .Y(n297));
  OAI21X1  g211(.A0(n296), .A1(n292), .B0(G219gat), .Y(n298));
  AND2X1   g212(.A(n292), .B(G228gat), .Y(n299));
  NOR3X1   g213(.A(n249), .B(n154), .C(n244), .Y(n300));
  AOI22X1  g214(.A0(n204), .A1(G165gat), .B0(G210gat), .B1(G91gat), .Y(n301));
  OAI21X1  g215(.A0(n249), .A1(n156), .B0(n301), .Y(n302));
  NOR3X1   g216(.A(n302), .B(n300), .C(n299), .Y(n303));
  OAI21X1  g217(.A0(n298), .A1(n297), .B0(n303), .Y(G879gat));
  XOR2X1   g218(.A(n253), .B(n245), .Y(n305));
  AND2X1   g219(.A(n263), .B(n260), .Y(n306));
  OR2X1    g220(.A(n265), .B(n306), .Y(n307));
  AND2X1   g221(.A(n307), .B(n305), .Y(n308));
  OAI21X1  g222(.A0(n307), .A1(n305), .B0(G219gat), .Y(n309));
  AND2X1   g223(.A(n305), .B(G228gat), .Y(n310));
  NOR3X1   g224(.A(n253), .B(n154), .C(n245), .Y(n311));
  AOI22X1  g225(.A0(n204), .A1(G171gat), .B0(G210gat), .B1(G96gat), .Y(n312));
  OAI21X1  g226(.A0(n253), .A1(n156), .B0(n312), .Y(n313));
  NOR3X1   g227(.A(n313), .B(n311), .C(n310), .Y(n314));
  OAI21X1  g228(.A0(n309), .A1(n308), .B0(n314), .Y(G880gat));
endmodule


