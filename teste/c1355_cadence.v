// Benchmark "c1355.blif" written by ABC on Fri Sep  1 22:17:29 2017

module \c1355.blif  ( 
    G1gat, G8gat, G15gat, G22gat, G29gat, G36gat, G43gat, G50gat, G57gat,
    G64gat, G71gat, G78gat, G85gat, G92gat, G99gat, G106gat, G113gat,
    G120gat, G127gat, G134gat, G141gat, G148gat, G155gat, G162gat, G169gat,
    G176gat, G183gat, G190gat, G197gat, G204gat, G211gat, G218gat, G225gat,
    G226gat, G227gat, G228gat, G229gat, G230gat, G231gat, G232gat, G233gat,
    G1324gat, G1325gat, G1326gat, G1327gat, G1328gat, G1329gat, G1330gat,
    G1331gat, G1332gat, G1333gat, G1334gat, G1335gat, G1336gat, G1337gat,
    G1338gat, G1339gat, G1340gat, G1341gat, G1342gat, G1343gat, G1344gat,
    G1345gat, G1346gat, G1347gat, G1348gat, G1349gat, G1350gat, G1351gat,
    G1352gat, G1353gat, G1354gat, G1355gat  );
  input  G1gat, G8gat, G15gat, G22gat, G29gat, G36gat, G43gat, G50gat,
    G57gat, G64gat, G71gat, G78gat, G85gat, G92gat, G99gat, G106gat,
    G113gat, G120gat, G127gat, G134gat, G141gat, G148gat, G155gat, G162gat,
    G169gat, G176gat, G183gat, G190gat, G197gat, G204gat, G211gat, G218gat,
    G225gat, G226gat, G227gat, G228gat, G229gat, G230gat, G231gat, G232gat,
    G233gat;
  output G1324gat, G1325gat, G1326gat, G1327gat, G1328gat, G1329gat, G1330gat,
    G1331gat, G1332gat, G1333gat, G1334gat, G1335gat, G1336gat, G1337gat,
    G1338gat, G1339gat, G1340gat, G1341gat, G1342gat, G1343gat, G1344gat,
    G1345gat, G1346gat, G1347gat, G1348gat, G1349gat, G1350gat, G1351gat,
    G1352gat, G1353gat, G1354gat, G1355gat;
  wire n74, n75, n76, n77, n78, n79, n80, n81, n82, n83, n84, n85, n86, n87,
    n88, n89, n90, n91, n92, n93, n94, n95, n96, n97, n98, n99, n100, n101,
    n102, n103, n104, n105, n106, n107, n108, n109, n110, n111, n112, n113,
    n114, n115, n116, n117, n118, n119, n120, n121, n122, n123, n124, n125,
    n126, n127, n128, n129, n130, n131, n132, n133, n134, n135, n136, n137,
    n138, n139, n140, n141, n142, n143, n144, n145, n146, n147, n148, n149,
    n150, n151, n152, n153, n154, n155, n156, n157, n158, n159, n160, n161,
    n162, n163, n164, n165, n166, n167, n168, n169, n170, n171, n172, n173,
    n174, n175, n177, n179, n181, n183, n184, n185, n186, n187, n188, n189,
    n190, n191, n192, n194, n196, n198, n200, n201, n203, n205, n207, n209,
    n210, n212, n214, n216, n218, n219, n220, n221, n222, n223, n224, n225,
    n226, n227, n229, n231, n233, n235, n236, n238, n240, n242, n244, n245,
    n247, n249, n251, n253, n254, n256, n258, n260;
  XOR2X1   g000(.A(G29gat), .B(G1gat), .Y(n74));
  XOR2X1   g001(.A(G85gat), .B(G57gat), .Y(n75));
  XOR2X1   g002(.A(n75), .B(n74), .Y(n76));
  AND2X1   g003(.A(G233gat), .B(G225gat), .Y(n77));
  INVX1    g004(.A(n77), .Y(n78));
  XOR2X1   g005(.A(G120gat), .B(G113gat), .Y(n79));
  XOR2X1   g006(.A(G134gat), .B(G127gat), .Y(n80));
  XOR2X1   g007(.A(n80), .B(n79), .Y(n81));
  XOR2X1   g008(.A(G148gat), .B(G141gat), .Y(n82));
  XOR2X1   g009(.A(G162gat), .B(G155gat), .Y(n83));
  XOR2X1   g010(.A(n83), .B(n82), .Y(n84));
  XOR2X1   g011(.A(n84), .B(n81), .Y(n85));
  XOR2X1   g012(.A(n85), .B(n78), .Y(n86));
  XOR2X1   g013(.A(n86), .B(n76), .Y(n87));
  XOR2X1   g014(.A(G50gat), .B(G22gat), .Y(n88));
  XOR2X1   g015(.A(G106gat), .B(G78gat), .Y(n89));
  XOR2X1   g016(.A(n89), .B(n88), .Y(n90));
  AND2X1   g017(.A(G233gat), .B(G228gat), .Y(n91));
  XOR2X1   g018(.A(G204gat), .B(G197gat), .Y(n92));
  XOR2X1   g019(.A(G218gat), .B(G211gat), .Y(n93));
  XOR2X1   g020(.A(n93), .B(n92), .Y(n94));
  XOR2X1   g021(.A(n94), .B(n84), .Y(n95));
  XOR2X1   g022(.A(n95), .B(n91), .Y(n96));
  XOR2X1   g023(.A(n96), .B(n90), .Y(n97));
  XOR2X1   g024(.A(G43gat), .B(G15gat), .Y(n98));
  XOR2X1   g025(.A(G99gat), .B(G71gat), .Y(n99));
  XOR2X1   g026(.A(n99), .B(n98), .Y(n100));
  AND2X1   g027(.A(G233gat), .B(G227gat), .Y(n101));
  XOR2X1   g028(.A(G176gat), .B(G169gat), .Y(n102));
  XOR2X1   g029(.A(G190gat), .B(G183gat), .Y(n103));
  XOR2X1   g030(.A(n103), .B(n102), .Y(n104));
  XOR2X1   g031(.A(n104), .B(n81), .Y(n105));
  XOR2X1   g032(.A(n105), .B(n101), .Y(n106));
  XOR2X1   g033(.A(n106), .B(n100), .Y(n107));
  XOR2X1   g034(.A(G36gat), .B(G8gat), .Y(n108));
  XOR2X1   g035(.A(G92gat), .B(G64gat), .Y(n109));
  XOR2X1   g036(.A(n109), .B(n108), .Y(n110));
  AND2X1   g037(.A(G233gat), .B(G226gat), .Y(n111));
  XOR2X1   g038(.A(n104), .B(n94), .Y(n112));
  XOR2X1   g039(.A(n112), .B(n111), .Y(n113));
  XOR2X1   g040(.A(n113), .B(n110), .Y(n114));
  NOR4X1   g041(.A(n114), .B(n107), .C(n97), .D(n87), .Y(n115));
  XOR2X1   g042(.A(n85), .B(n77), .Y(n116));
  XOR2X1   g043(.A(n116), .B(n76), .Y(n117));
  INVX1    g044(.A(n111), .Y(n118));
  XOR2X1   g045(.A(n112), .B(n118), .Y(n119));
  XOR2X1   g046(.A(n119), .B(n110), .Y(n120));
  NOR4X1   g047(.A(n120), .B(n107), .C(n97), .D(n117), .Y(n121));
  INVX1    g048(.A(n91), .Y(n122));
  XOR2X1   g049(.A(n95), .B(n122), .Y(n123));
  XOR2X1   g050(.A(n123), .B(n90), .Y(n124));
  NOR4X1   g051(.A(n114), .B(n107), .C(n124), .D(n117), .Y(n125));
  INVX1    g052(.A(n101), .Y(n126));
  XOR2X1   g053(.A(n105), .B(n126), .Y(n127));
  XOR2X1   g054(.A(n127), .B(n100), .Y(n128));
  NOR4X1   g055(.A(n114), .B(n128), .C(n97), .D(n117), .Y(n129));
  NOR4X1   g056(.A(n129), .B(n125), .C(n121), .D(n115), .Y(n130));
  XOR2X1   g057(.A(G141gat), .B(G113gat), .Y(n131));
  XOR2X1   g058(.A(G197gat), .B(G169gat), .Y(n132));
  XOR2X1   g059(.A(n132), .B(n131), .Y(n133));
  AND2X1   g060(.A(G233gat), .B(G229gat), .Y(n134));
  INVX1    g061(.A(n134), .Y(n135));
  XOR2X1   g062(.A(G8gat), .B(G1gat), .Y(n136));
  XOR2X1   g063(.A(G22gat), .B(G15gat), .Y(n137));
  XOR2X1   g064(.A(n137), .B(n136), .Y(n138));
  XOR2X1   g065(.A(G36gat), .B(G29gat), .Y(n139));
  XOR2X1   g066(.A(G50gat), .B(G43gat), .Y(n140));
  XOR2X1   g067(.A(n140), .B(n139), .Y(n141));
  XOR2X1   g068(.A(n141), .B(n138), .Y(n142));
  XOR2X1   g069(.A(n142), .B(n135), .Y(n143));
  XOR2X1   g070(.A(n143), .B(n133), .Y(n144));
  XOR2X1   g071(.A(G148gat), .B(G120gat), .Y(n145));
  XOR2X1   g072(.A(G204gat), .B(G176gat), .Y(n146));
  XOR2X1   g073(.A(n146), .B(n145), .Y(n147));
  AND2X1   g074(.A(G233gat), .B(G230gat), .Y(n148));
  XOR2X1   g075(.A(G64gat), .B(G57gat), .Y(n149));
  XOR2X1   g076(.A(G78gat), .B(G71gat), .Y(n150));
  XOR2X1   g077(.A(n150), .B(n149), .Y(n151));
  XOR2X1   g078(.A(G92gat), .B(G85gat), .Y(n152));
  XOR2X1   g079(.A(G106gat), .B(G99gat), .Y(n153));
  XOR2X1   g080(.A(n153), .B(n152), .Y(n154));
  XOR2X1   g081(.A(n154), .B(n151), .Y(n155));
  XOR2X1   g082(.A(n155), .B(n148), .Y(n156));
  XOR2X1   g083(.A(n156), .B(n147), .Y(n157));
  OR2X1    g084(.A(n157), .B(n144), .Y(n158));
  XOR2X1   g085(.A(G162gat), .B(G134gat), .Y(n159));
  XOR2X1   g086(.A(G218gat), .B(G190gat), .Y(n160));
  XOR2X1   g087(.A(n160), .B(n159), .Y(n161));
  AND2X1   g088(.A(G233gat), .B(G232gat), .Y(n162));
  XOR2X1   g089(.A(n154), .B(n141), .Y(n163));
  XOR2X1   g090(.A(n163), .B(n162), .Y(n164));
  XOR2X1   g091(.A(n164), .B(n161), .Y(n165));
  XOR2X1   g092(.A(G155gat), .B(G127gat), .Y(n166));
  XOR2X1   g093(.A(G211gat), .B(G183gat), .Y(n167));
  XOR2X1   g094(.A(n167), .B(n166), .Y(n168));
  AND2X1   g095(.A(G233gat), .B(G231gat), .Y(n169));
  INVX1    g096(.A(n169), .Y(n170));
  XOR2X1   g097(.A(n151), .B(n138), .Y(n171));
  XOR2X1   g098(.A(n171), .B(n170), .Y(n172));
  XOR2X1   g099(.A(n172), .B(n168), .Y(n173));
  OR2X1    g100(.A(n173), .B(n165), .Y(n174));
  NOR4X1   g101(.A(n174), .B(n158), .C(n130), .D(n87), .Y(n175));
  XOR2X1   g102(.A(n175), .B(G1gat), .Y(G1324gat));
  NOR4X1   g103(.A(n174), .B(n158), .C(n130), .D(n120), .Y(n177));
  XOR2X1   g104(.A(n177), .B(G8gat), .Y(G1325gat));
  NOR4X1   g105(.A(n174), .B(n158), .C(n130), .D(n128), .Y(n179));
  XOR2X1   g106(.A(n179), .B(G15gat), .Y(G1326gat));
  NOR4X1   g107(.A(n174), .B(n158), .C(n130), .D(n124), .Y(n181));
  XOR2X1   g108(.A(n181), .B(G22gat), .Y(G1327gat));
  INVX1    g109(.A(n162), .Y(n183));
  XOR2X1   g110(.A(n163), .B(n183), .Y(n184));
  XOR2X1   g111(.A(n184), .B(n161), .Y(n185));
  XOR2X1   g112(.A(n142), .B(n134), .Y(n186));
  XOR2X1   g113(.A(n186), .B(n133), .Y(n187));
  INVX1    g114(.A(n148), .Y(n188));
  XOR2X1   g115(.A(n155), .B(n188), .Y(n189));
  XOR2X1   g116(.A(n189), .B(n147), .Y(n190));
  NAND3X1  g117(.A(n173), .B(n190), .C(n187), .Y(n191));
  NOR4X1   g118(.A(n191), .B(n185), .C(n130), .D(n87), .Y(n192));
  XOR2X1   g119(.A(n192), .B(G29gat), .Y(G1328gat));
  NOR4X1   g120(.A(n191), .B(n185), .C(n130), .D(n120), .Y(n194));
  XOR2X1   g121(.A(n194), .B(G36gat), .Y(G1329gat));
  NOR4X1   g122(.A(n191), .B(n185), .C(n130), .D(n128), .Y(n196));
  XOR2X1   g123(.A(n196), .B(G43gat), .Y(G1330gat));
  NOR4X1   g124(.A(n191), .B(n185), .C(n130), .D(n124), .Y(n198));
  XOR2X1   g125(.A(n198), .B(G50gat), .Y(G1331gat));
  OR2X1    g126(.A(n190), .B(n187), .Y(n200));
  NOR4X1   g127(.A(n200), .B(n174), .C(n130), .D(n87), .Y(n201));
  XOR2X1   g128(.A(n201), .B(G57gat), .Y(G1332gat));
  NOR4X1   g129(.A(n200), .B(n174), .C(n130), .D(n120), .Y(n203));
  XOR2X1   g130(.A(n203), .B(G64gat), .Y(G1333gat));
  NOR4X1   g131(.A(n200), .B(n174), .C(n130), .D(n128), .Y(n205));
  XOR2X1   g132(.A(n205), .B(G71gat), .Y(G1334gat));
  NOR4X1   g133(.A(n200), .B(n174), .C(n130), .D(n124), .Y(n207));
  XOR2X1   g134(.A(n207), .B(G78gat), .Y(G1335gat));
  NAND3X1  g135(.A(n173), .B(n157), .C(n144), .Y(n209));
  NOR4X1   g136(.A(n209), .B(n185), .C(n130), .D(n87), .Y(n210));
  XOR2X1   g137(.A(n210), .B(G85gat), .Y(G1336gat));
  NOR4X1   g138(.A(n209), .B(n185), .C(n130), .D(n120), .Y(n212));
  XOR2X1   g139(.A(n212), .B(G92gat), .Y(G1337gat));
  NOR4X1   g140(.A(n209), .B(n185), .C(n130), .D(n128), .Y(n214));
  XOR2X1   g141(.A(n214), .B(G99gat), .Y(G1338gat));
  NOR4X1   g142(.A(n209), .B(n185), .C(n130), .D(n124), .Y(n216));
  XOR2X1   g143(.A(n216), .B(G106gat), .Y(G1339gat));
  OR2X1    g144(.A(n114), .B(n87), .Y(n218));
  OR2X1    g145(.A(n128), .B(n97), .Y(n219));
  XOR2X1   g146(.A(n171), .B(n169), .Y(n220));
  XOR2X1   g147(.A(n220), .B(n168), .Y(n221));
  NOR4X1   g148(.A(n221), .B(n165), .C(n157), .D(n144), .Y(n222));
  NOR4X1   g149(.A(n221), .B(n165), .C(n190), .D(n187), .Y(n223));
  NOR4X1   g150(.A(n221), .B(n185), .C(n157), .D(n187), .Y(n224));
  NOR4X1   g151(.A(n173), .B(n165), .C(n157), .D(n187), .Y(n225));
  NOR4X1   g152(.A(n225), .B(n224), .C(n223), .D(n222), .Y(n226));
  NOR4X1   g153(.A(n226), .B(n144), .C(n219), .D(n218), .Y(n227));
  XOR2X1   g154(.A(n227), .B(G113gat), .Y(G1340gat));
  NOR4X1   g155(.A(n226), .B(n190), .C(n219), .D(n218), .Y(n229));
  XOR2X1   g156(.A(n229), .B(G120gat), .Y(G1341gat));
  NOR4X1   g157(.A(n226), .B(n173), .C(n219), .D(n218), .Y(n231));
  XOR2X1   g158(.A(n231), .B(G127gat), .Y(G1342gat));
  NOR4X1   g159(.A(n226), .B(n185), .C(n219), .D(n218), .Y(n233));
  XOR2X1   g160(.A(n233), .B(G134gat), .Y(G1343gat));
  NAND3X1  g161(.A(n120), .B(n128), .C(n117), .Y(n235));
  NOR4X1   g162(.A(n226), .B(n144), .C(n235), .D(n124), .Y(n236));
  XOR2X1   g163(.A(n236), .B(G141gat), .Y(G1344gat));
  NOR4X1   g164(.A(n226), .B(n190), .C(n235), .D(n124), .Y(n238));
  XOR2X1   g165(.A(n238), .B(G148gat), .Y(G1345gat));
  NOR4X1   g166(.A(n226), .B(n173), .C(n235), .D(n124), .Y(n240));
  XOR2X1   g167(.A(n240), .B(G155gat), .Y(G1346gat));
  NOR4X1   g168(.A(n226), .B(n185), .C(n235), .D(n124), .Y(n242));
  XOR2X1   g169(.A(n242), .B(G162gat), .Y(G1347gat));
  OR2X1    g170(.A(n120), .B(n117), .Y(n244));
  NOR4X1   g171(.A(n226), .B(n144), .C(n219), .D(n244), .Y(n245));
  XOR2X1   g172(.A(n245), .B(G169gat), .Y(G1348gat));
  NOR4X1   g173(.A(n226), .B(n190), .C(n219), .D(n244), .Y(n247));
  XOR2X1   g174(.A(n247), .B(G176gat), .Y(G1349gat));
  NOR4X1   g175(.A(n226), .B(n173), .C(n219), .D(n244), .Y(n249));
  XOR2X1   g176(.A(n249), .B(G183gat), .Y(G1350gat));
  NOR4X1   g177(.A(n226), .B(n185), .C(n219), .D(n244), .Y(n251));
  XOR2X1   g178(.A(n251), .B(G190gat), .Y(G1351gat));
  NAND3X1  g179(.A(n114), .B(n128), .C(n87), .Y(n253));
  NOR4X1   g180(.A(n226), .B(n144), .C(n253), .D(n124), .Y(n254));
  XOR2X1   g181(.A(n254), .B(G197gat), .Y(G1352gat));
  NOR4X1   g182(.A(n226), .B(n190), .C(n253), .D(n124), .Y(n256));
  XOR2X1   g183(.A(n256), .B(G204gat), .Y(G1353gat));
  NOR4X1   g184(.A(n226), .B(n173), .C(n253), .D(n124), .Y(n258));
  XOR2X1   g185(.A(n258), .B(G211gat), .Y(G1354gat));
  NOR4X1   g186(.A(n226), .B(n185), .C(n253), .D(n124), .Y(n260));
  XOR2X1   g187(.A(n260), .B(G218gat), .Y(G1355gat));
endmodule


