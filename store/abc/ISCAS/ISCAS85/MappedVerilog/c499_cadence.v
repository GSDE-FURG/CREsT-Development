// Benchmark "c499.blif" written by ABC on Fri Sep  1 22:12:46 2017

module \c499.blif  ( 
    Gid0, Gid1, Gid2, Gid3, Gid4, Gid5, Gid6, Gid7, Gid8, Gid9, Gid10,
    Gid11, Gid12, Gid13, Gid14, Gid15, Gid16, Gid17, Gid18, Gid19, Gid20,
    Gid21, Gid22, Gid23, Gid24, Gid25, Gid26, Gid27, Gid28, Gid29, Gid30,
    Gid31, Gic0, Gic1, Gic2, Gic3, Gic4, Gic5, Gic6, Gic7, Gr,
    God0, God1, God2, God3, God4, God5, God6, God7, God8, God9, God10,
    God11, God12, God13, God14, God15, God16, God17, God18, God19, God20,
    God21, God22, God23, God24, God25, God26, God27, God28, God29, God30,
    God31  );
  input  Gid0, Gid1, Gid2, Gid3, Gid4, Gid5, Gid6, Gid7, Gid8, Gid9,
    Gid10, Gid11, Gid12, Gid13, Gid14, Gid15, Gid16, Gid17, Gid18, Gid19,
    Gid20, Gid21, Gid22, Gid23, Gid24, Gid25, Gid26, Gid27, Gid28, Gid29,
    Gid30, Gid31, Gic0, Gic1, Gic2, Gic3, Gic4, Gic5, Gic6, Gic7, Gr;
  output God0, God1, God2, God3, God4, God5, God6, God7, God8, God9, God10,
    God11, God12, God13, God14, God15, God16, God17, God18, God19, God20,
    God21, God22, God23, God24, God25, God26, God27, God28, God29, God30,
    God31;
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
  XOR2X1   g000(.A(Gid4), .B(Gid0), .Y(n74));
  XOR2X1   g001(.A(Gid12), .B(Gid8), .Y(n75));
  XOR2X1   g002(.A(n75), .B(n74), .Y(n76));
  AND2X1   g003(.A(Gr), .B(Gic0), .Y(n77));
  INVX1    g004(.A(n77), .Y(n78));
  XOR2X1   g005(.A(Gid17), .B(Gid16), .Y(n79));
  XOR2X1   g006(.A(Gid19), .B(Gid18), .Y(n80));
  XOR2X1   g007(.A(n80), .B(n79), .Y(n81));
  XOR2X1   g008(.A(Gid21), .B(Gid20), .Y(n82));
  XOR2X1   g009(.A(Gid23), .B(Gid22), .Y(n83));
  XOR2X1   g010(.A(n83), .B(n82), .Y(n84));
  XOR2X1   g011(.A(n84), .B(n81), .Y(n85));
  XOR2X1   g012(.A(n85), .B(n78), .Y(n86));
  XOR2X1   g013(.A(n86), .B(n76), .Y(n87));
  XOR2X1   g014(.A(Gid7), .B(Gid3), .Y(n88));
  XOR2X1   g015(.A(Gid15), .B(Gid11), .Y(n89));
  XOR2X1   g016(.A(n89), .B(n88), .Y(n90));
  AND2X1   g017(.A(Gr), .B(Gic3), .Y(n91));
  XOR2X1   g018(.A(Gid29), .B(Gid28), .Y(n92));
  XOR2X1   g019(.A(Gid31), .B(Gid30), .Y(n93));
  XOR2X1   g020(.A(n93), .B(n92), .Y(n94));
  XOR2X1   g021(.A(n94), .B(n84), .Y(n95));
  XOR2X1   g022(.A(n95), .B(n91), .Y(n96));
  XOR2X1   g023(.A(n96), .B(n90), .Y(n97));
  XOR2X1   g024(.A(Gid6), .B(Gid2), .Y(n98));
  XOR2X1   g025(.A(Gid14), .B(Gid10), .Y(n99));
  XOR2X1   g026(.A(n99), .B(n98), .Y(n100));
  AND2X1   g027(.A(Gr), .B(Gic2), .Y(n101));
  XOR2X1   g028(.A(Gid25), .B(Gid24), .Y(n102));
  XOR2X1   g029(.A(Gid27), .B(Gid26), .Y(n103));
  XOR2X1   g030(.A(n103), .B(n102), .Y(n104));
  XOR2X1   g031(.A(n104), .B(n81), .Y(n105));
  XOR2X1   g032(.A(n105), .B(n101), .Y(n106));
  XOR2X1   g033(.A(n106), .B(n100), .Y(n107));
  XOR2X1   g034(.A(Gid5), .B(Gid1), .Y(n108));
  XOR2X1   g035(.A(Gid13), .B(Gid9), .Y(n109));
  XOR2X1   g036(.A(n109), .B(n108), .Y(n110));
  AND2X1   g037(.A(Gr), .B(Gic1), .Y(n111));
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
  XOR2X1   g057(.A(Gid20), .B(Gid16), .Y(n131));
  XOR2X1   g058(.A(Gid28), .B(Gid24), .Y(n132));
  XOR2X1   g059(.A(n132), .B(n131), .Y(n133));
  AND2X1   g060(.A(Gr), .B(Gic4), .Y(n134));
  INVX1    g061(.A(n134), .Y(n135));
  XOR2X1   g062(.A(Gid5), .B(Gid4), .Y(n136));
  XOR2X1   g063(.A(Gid7), .B(Gid6), .Y(n137));
  XOR2X1   g064(.A(n137), .B(n136), .Y(n138));
  XOR2X1   g065(.A(Gid1), .B(Gid0), .Y(n139));
  XOR2X1   g066(.A(Gid3), .B(Gid2), .Y(n140));
  XOR2X1   g067(.A(n140), .B(n139), .Y(n141));
  XOR2X1   g068(.A(n141), .B(n138), .Y(n142));
  XOR2X1   g069(.A(n142), .B(n135), .Y(n143));
  XOR2X1   g070(.A(n143), .B(n133), .Y(n144));
  XOR2X1   g071(.A(Gid21), .B(Gid17), .Y(n145));
  XOR2X1   g072(.A(Gid29), .B(Gid25), .Y(n146));
  XOR2X1   g073(.A(n146), .B(n145), .Y(n147));
  AND2X1   g074(.A(Gr), .B(Gic5), .Y(n148));
  XOR2X1   g075(.A(Gid13), .B(Gid12), .Y(n149));
  XOR2X1   g076(.A(Gid15), .B(Gid14), .Y(n150));
  XOR2X1   g077(.A(n150), .B(n149), .Y(n151));
  XOR2X1   g078(.A(Gid9), .B(Gid8), .Y(n152));
  XOR2X1   g079(.A(Gid11), .B(Gid10), .Y(n153));
  XOR2X1   g080(.A(n153), .B(n152), .Y(n154));
  XOR2X1   g081(.A(n154), .B(n151), .Y(n155));
  XOR2X1   g082(.A(n155), .B(n148), .Y(n156));
  XOR2X1   g083(.A(n156), .B(n147), .Y(n157));
  OR2X1    g084(.A(n157), .B(n144), .Y(n158));
  XOR2X1   g085(.A(Gid23), .B(Gid19), .Y(n159));
  XOR2X1   g086(.A(Gid31), .B(Gid27), .Y(n160));
  XOR2X1   g087(.A(n160), .B(n159), .Y(n161));
  AND2X1   g088(.A(Gr), .B(Gic7), .Y(n162));
  XOR2X1   g089(.A(n151), .B(n138), .Y(n163));
  XOR2X1   g090(.A(n163), .B(n162), .Y(n164));
  XOR2X1   g091(.A(n164), .B(n161), .Y(n165));
  XOR2X1   g092(.A(Gid22), .B(Gid18), .Y(n166));
  XOR2X1   g093(.A(Gid30), .B(Gid26), .Y(n167));
  XOR2X1   g094(.A(n167), .B(n166), .Y(n168));
  AND2X1   g095(.A(Gr), .B(Gic6), .Y(n169));
  INVX1    g096(.A(n169), .Y(n170));
  XOR2X1   g097(.A(n154), .B(n141), .Y(n171));
  XOR2X1   g098(.A(n171), .B(n170), .Y(n172));
  XOR2X1   g099(.A(n172), .B(n168), .Y(n173));
  OR2X1    g100(.A(n173), .B(n165), .Y(n174));
  NOR4X1   g101(.A(n174), .B(n158), .C(n130), .D(n87), .Y(n175));
  XOR2X1   g102(.A(n175), .B(Gid0), .Y(God0));
  NOR4X1   g103(.A(n174), .B(n158), .C(n130), .D(n120), .Y(n177));
  XOR2X1   g104(.A(n177), .B(Gid1), .Y(God1));
  NOR4X1   g105(.A(n174), .B(n158), .C(n130), .D(n128), .Y(n179));
  XOR2X1   g106(.A(n179), .B(Gid2), .Y(God2));
  NOR4X1   g107(.A(n174), .B(n158), .C(n130), .D(n124), .Y(n181));
  XOR2X1   g108(.A(n181), .B(Gid3), .Y(God3));
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
  XOR2X1   g119(.A(n192), .B(Gid4), .Y(God4));
  NOR4X1   g120(.A(n191), .B(n185), .C(n130), .D(n120), .Y(n194));
  XOR2X1   g121(.A(n194), .B(Gid5), .Y(God5));
  NOR4X1   g122(.A(n191), .B(n185), .C(n130), .D(n128), .Y(n196));
  XOR2X1   g123(.A(n196), .B(Gid6), .Y(God6));
  NOR4X1   g124(.A(n191), .B(n185), .C(n130), .D(n124), .Y(n198));
  XOR2X1   g125(.A(n198), .B(Gid7), .Y(God7));
  OR2X1    g126(.A(n190), .B(n187), .Y(n200));
  NOR4X1   g127(.A(n200), .B(n174), .C(n130), .D(n87), .Y(n201));
  XOR2X1   g128(.A(n201), .B(Gid8), .Y(God8));
  NOR4X1   g129(.A(n200), .B(n174), .C(n130), .D(n120), .Y(n203));
  XOR2X1   g130(.A(n203), .B(Gid9), .Y(God9));
  NOR4X1   g131(.A(n200), .B(n174), .C(n130), .D(n128), .Y(n205));
  XOR2X1   g132(.A(n205), .B(Gid10), .Y(God10));
  NOR4X1   g133(.A(n200), .B(n174), .C(n130), .D(n124), .Y(n207));
  XOR2X1   g134(.A(n207), .B(Gid11), .Y(God11));
  NAND3X1  g135(.A(n173), .B(n157), .C(n144), .Y(n209));
  NOR4X1   g136(.A(n209), .B(n185), .C(n130), .D(n87), .Y(n210));
  XOR2X1   g137(.A(n210), .B(Gid12), .Y(God12));
  NOR4X1   g138(.A(n209), .B(n185), .C(n130), .D(n120), .Y(n212));
  XOR2X1   g139(.A(n212), .B(Gid13), .Y(God13));
  NOR4X1   g140(.A(n209), .B(n185), .C(n130), .D(n128), .Y(n214));
  XOR2X1   g141(.A(n214), .B(Gid14), .Y(God14));
  NOR4X1   g142(.A(n209), .B(n185), .C(n130), .D(n124), .Y(n216));
  XOR2X1   g143(.A(n216), .B(Gid15), .Y(God15));
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
  XOR2X1   g154(.A(n227), .B(Gid16), .Y(God16));
  NOR4X1   g155(.A(n226), .B(n190), .C(n219), .D(n218), .Y(n229));
  XOR2X1   g156(.A(n229), .B(Gid17), .Y(God17));
  NOR4X1   g157(.A(n226), .B(n173), .C(n219), .D(n218), .Y(n231));
  XOR2X1   g158(.A(n231), .B(Gid18), .Y(God18));
  NOR4X1   g159(.A(n226), .B(n185), .C(n219), .D(n218), .Y(n233));
  XOR2X1   g160(.A(n233), .B(Gid19), .Y(God19));
  NAND3X1  g161(.A(n120), .B(n128), .C(n117), .Y(n235));
  NOR4X1   g162(.A(n226), .B(n144), .C(n235), .D(n124), .Y(n236));
  XOR2X1   g163(.A(n236), .B(Gid20), .Y(God20));
  NOR4X1   g164(.A(n226), .B(n190), .C(n235), .D(n124), .Y(n238));
  XOR2X1   g165(.A(n238), .B(Gid21), .Y(God21));
  NOR4X1   g166(.A(n226), .B(n173), .C(n235), .D(n124), .Y(n240));
  XOR2X1   g167(.A(n240), .B(Gid22), .Y(God22));
  NOR4X1   g168(.A(n226), .B(n185), .C(n235), .D(n124), .Y(n242));
  XOR2X1   g169(.A(n242), .B(Gid23), .Y(God23));
  OR2X1    g170(.A(n120), .B(n117), .Y(n244));
  NOR4X1   g171(.A(n226), .B(n144), .C(n219), .D(n244), .Y(n245));
  XOR2X1   g172(.A(n245), .B(Gid24), .Y(God24));
  NOR4X1   g173(.A(n226), .B(n190), .C(n219), .D(n244), .Y(n247));
  XOR2X1   g174(.A(n247), .B(Gid25), .Y(God25));
  NOR4X1   g175(.A(n226), .B(n173), .C(n219), .D(n244), .Y(n249));
  XOR2X1   g176(.A(n249), .B(Gid26), .Y(God26));
  NOR4X1   g177(.A(n226), .B(n185), .C(n219), .D(n244), .Y(n251));
  XOR2X1   g178(.A(n251), .B(Gid27), .Y(God27));
  NAND3X1  g179(.A(n114), .B(n128), .C(n87), .Y(n253));
  NOR4X1   g180(.A(n226), .B(n144), .C(n253), .D(n124), .Y(n254));
  XOR2X1   g181(.A(n254), .B(Gid28), .Y(God28));
  NOR4X1   g182(.A(n226), .B(n190), .C(n253), .D(n124), .Y(n256));
  XOR2X1   g183(.A(n256), .B(Gid29), .Y(God29));
  NOR4X1   g184(.A(n226), .B(n173), .C(n253), .D(n124), .Y(n258));
  XOR2X1   g185(.A(n258), .B(Gid30), .Y(God30));
  NOR4X1   g186(.A(n226), .B(n185), .C(n253), .D(n124), .Y(n260));
  XOR2X1   g187(.A(n260), .B(Gid31), .Y(God31));
endmodule


