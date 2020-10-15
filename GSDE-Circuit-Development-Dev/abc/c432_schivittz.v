// Benchmark "c432.blif" written by ABC on Sun Nov 10 20:08:59 2019

module \c432.blif  ( 
    G1gat, G4gat, G8gat, G11gat, G14gat, G17gat, G21gat, G24gat, G27gat,
    G30gat, G34gat, G37gat, G40gat, G43gat, G47gat, G50gat, G53gat, G56gat,
    G60gat, G63gat, G66gat, G69gat, G73gat, G76gat, G79gat, G82gat, G86gat,
    G89gat, G92gat, G95gat, G99gat, G102gat, G105gat, G108gat, G112gat,
    G115gat,
    G223gat, G329gat, G370gat, G421gat, G430gat, G431gat, G432gat  );
  input  G1gat, G4gat, G8gat, G11gat, G14gat, G17gat, G21gat, G24gat,
    G27gat, G30gat, G34gat, G37gat, G40gat, G43gat, G47gat, G50gat, G53gat,
    G56gat, G60gat, G63gat, G66gat, G69gat, G73gat, G76gat, G79gat, G82gat,
    G86gat, G89gat, G92gat, G95gat, G99gat, G102gat, G105gat, G108gat,
    G112gat, G115gat;
  output G223gat, G329gat, G370gat, G421gat, G430gat, G431gat, G432gat;
  wire n44, n45, n46, n47, n48, n49, n50, n51, n52, n53, n54, n55, n56, n57,
    n58, n59, n60, n61, n62, n64, n65, n66, n67, n68, n69, n70, n71, n72,
    n73, n74, n75, n76, n77, n78, n79, n80, n81, n82, n83, n84, n85, n86,
    n87, n88, n89, n90, n91, n92, n93, n94, n95, n96, n97, n98, n99, n100,
    n101, n102, n103, n104, n105, n106, n107, n109, n110, n111, n112, n113,
    n114, n115, n116, n117, n118, n119, n120, n121, n122, n123, n124, n125,
    n126, n127, n128, n129, n130, n131, n132, n133, n134, n135, n136, n137,
    n138, n139, n140, n141, n142, n143, n144, n145, n146, n147, n148, n149,
    n150, n151, n152, n153, n154, n156, n157, n158, n159, n160, n161, n162,
    n163, n164, n165, n166, n167, n168, n169, n170, n171, n172, n173, n174,
    n175, n176, n177, n178, n179, n180, n181, n182, n183, n184, n185, n186,
    n187, n188, n189, n190, n192, n193, n194, n195, n196, n197, n198, n199,
    n200, n201, n202, n203, n204, n205, n206, n207, n208, n209, n210, n211,
    n212, n213, n214, n216, n217, n219, n220, n221;
  INVX1    g000(.A(G17gat), .Y(n44));
  NOR2X1   g001(.A(n44), .B(G11gat), .Y(n45));
  INVX1    g002(.A(G4gat), .Y(n46));
  INVX1    g003(.A(G30gat), .Y(n47));
  OAI22X1  g004(.A0(n47), .A1(G24gat), .B0(n46), .B1(G1gat), .Y(n48));
  NOR2X1   g005(.A(n48), .B(n45), .Y(n49));
  INVX1    g006(.A(G108gat), .Y(n50));
  NOR2X1   g007(.A(n50), .B(G102gat), .Y(n51));
  INVX1    g008(.A(G95gat), .Y(n52));
  NOR2X1   g009(.A(n52), .B(G89gat), .Y(n53));
  INVX1    g010(.A(G76gat), .Y(n54));
  NAND2X1  g011(.A(G82gat), .B(n54), .Y(n55));
  INVX1    g012(.A(G63gat), .Y(n56));
  NAND2X1  g013(.A(G69gat), .B(n56), .Y(n57));
  INVX1    g014(.A(G37gat), .Y(n58));
  INVX1    g015(.A(G50gat), .Y(n59));
  AOI22X1  g016(.A0(G56gat), .A1(n59), .B0(G43gat), .B1(n58), .Y(n60));
  NAND3X1  g017(.A(n60), .B(n57), .C(n55), .Y(n61));
  NOR3X1   g018(.A(n61), .B(n53), .C(n51), .Y(n62));
  NAND2X1  g019(.A(n62), .B(n49), .Y(G223gat));
  INVX1    g020(.A(G21gat), .Y(n64));
  INVX1    g021(.A(G89gat), .Y(n65));
  INVX1    g022(.A(G102gat), .Y(n66));
  AOI22X1  g023(.A0(G108gat), .A1(n66), .B0(G95gat), .B1(n65), .Y(n67));
  NAND4X1  g024(.A(n60), .B(n57), .C(n55), .D(n67), .Y(n68));
  OAI22X1  g025(.A0(n68), .A1(n48), .B0(n44), .B1(G11gat), .Y(n69));
  NAND3X1  g026(.A(n69), .B(n64), .C(G17gat), .Y(n70));
  NOR2X1   g027(.A(n47), .B(G24gat), .Y(n71));
  NOR2X1   g028(.A(n46), .B(G1gat), .Y(n72));
  NOR4X1   g029(.A(n68), .B(n72), .C(n71), .D(n45), .Y(n73));
  NOR2X1   g030(.A(n47), .B(G24gat), .Y(n74));
  NOR2X1   g031(.A(G34gat), .B(n47), .Y(n75));
  OAI21X1  g032(.A0(n74), .A1(n73), .B0(n75), .Y(n76));
  NOR4X1   g033(.A(n68), .B(n72), .C(n71), .D(n45), .Y(n77));
  NOR2X1   g034(.A(n46), .B(G1gat), .Y(n78));
  NOR2X1   g035(.A(G8gat), .B(n46), .Y(n79));
  OAI21X1  g036(.A0(n78), .A1(n77), .B0(n79), .Y(n80));
  INVX1    g037(.A(n51), .Y(n81));
  NOR3X1   g038(.A(n68), .B(n48), .C(n45), .Y(n82));
  XOR2X1   g039(.A(n82), .B(n81), .Y(n83));
  INVX1    g040(.A(G112gat), .Y(n84));
  NAND2X1  g041(.A(n84), .B(G108gat), .Y(n85));
  INVX1    g042(.A(n53), .Y(n86));
  XOR2X1   g043(.A(n82), .B(n86), .Y(n87));
  INVX1    g044(.A(G99gat), .Y(n88));
  NAND2X1  g045(.A(n88), .B(G95gat), .Y(n89));
  OAI22X1  g046(.A0(n89), .A1(n87), .B0(n85), .B1(n83), .Y(n90));
  XOR2X1   g047(.A(n82), .B(n55), .Y(n91));
  INVX1    g048(.A(G86gat), .Y(n92));
  NAND2X1  g049(.A(n92), .B(G82gat), .Y(n93));
  XOR2X1   g050(.A(n82), .B(n57), .Y(n94));
  INVX1    g051(.A(G73gat), .Y(n95));
  NAND2X1  g052(.A(n95), .B(G69gat), .Y(n96));
  OAI22X1  g053(.A0(n96), .A1(n94), .B0(n93), .B1(n91), .Y(n97));
  NAND2X1  g054(.A(G56gat), .B(n59), .Y(n98));
  XOR2X1   g055(.A(n82), .B(n98), .Y(n99));
  INVX1    g056(.A(G60gat), .Y(n100));
  NAND2X1  g057(.A(n100), .B(G56gat), .Y(n101));
  NAND2X1  g058(.A(G43gat), .B(n58), .Y(n102));
  XOR2X1   g059(.A(n82), .B(n102), .Y(n103));
  INVX1    g060(.A(G47gat), .Y(n104));
  NAND2X1  g061(.A(n104), .B(G43gat), .Y(n105));
  OAI22X1  g062(.A0(n105), .A1(n103), .B0(n101), .B1(n99), .Y(n106));
  NOR3X1   g063(.A(n106), .B(n97), .C(n90), .Y(n107));
  NAND4X1  g064(.A(n107), .B(n80), .C(n76), .D(n70), .Y(G329gat));
  INVX1    g065(.A(G27gat), .Y(n109));
  XOR2X1   g066(.A(G329gat), .B(n70), .Y(n110));
  NAND4X1  g067(.A(n110), .B(n69), .C(n109), .D(G17gat), .Y(n111));
  INVX1    g068(.A(n76), .Y(n112));
  NAND3X1  g069(.A(n80), .B(n76), .C(n70), .Y(n113));
  NOR4X1   g070(.A(n106), .B(n97), .C(n90), .D(n113), .Y(n114));
  XOR2X1   g071(.A(n114), .B(n112), .Y(n115));
  NOR2X1   g072(.A(n74), .B(n73), .Y(n116));
  NOR3X1   g073(.A(n116), .B(G40gat), .C(n47), .Y(n117));
  INVX1    g074(.A(n80), .Y(n118));
  XOR2X1   g075(.A(n114), .B(n118), .Y(n119));
  NOR2X1   g076(.A(n78), .B(n77), .Y(n120));
  NOR3X1   g077(.A(n120), .B(G14gat), .C(n46), .Y(n121));
  AOI22X1  g078(.A0(n121), .A1(n119), .B0(n117), .B1(n115), .Y(n122));
  XOR2X1   g079(.A(n82), .B(n51), .Y(n123));
  NAND3X1  g080(.A(n123), .B(n84), .C(G108gat), .Y(n124));
  XOR2X1   g081(.A(n114), .B(n124), .Y(n125));
  NOR4X1   g082(.A(n125), .B(n83), .C(G115gat), .D(n50), .Y(n126));
  XOR2X1   g083(.A(n82), .B(n53), .Y(n127));
  NAND3X1  g084(.A(n127), .B(n88), .C(G95gat), .Y(n128));
  XOR2X1   g085(.A(n114), .B(n128), .Y(n129));
  NOR4X1   g086(.A(n129), .B(n87), .C(G105gat), .D(n52), .Y(n130));
  NOR2X1   g087(.A(n130), .B(n126), .Y(n131));
  XOR2X1   g088(.A(G223gat), .B(n55), .Y(n132));
  NAND3X1  g089(.A(n132), .B(n92), .C(G82gat), .Y(n133));
  XOR2X1   g090(.A(n114), .B(n133), .Y(n134));
  INVX1    g091(.A(G92gat), .Y(n135));
  NAND3X1  g092(.A(n132), .B(n135), .C(G82gat), .Y(n136));
  NOR2X1   g093(.A(n136), .B(n134), .Y(n137));
  INVX1    g094(.A(G69gat), .Y(n138));
  XOR2X1   g095(.A(G223gat), .B(n57), .Y(n139));
  NAND3X1  g096(.A(n139), .B(n95), .C(G69gat), .Y(n140));
  XOR2X1   g097(.A(n114), .B(n140), .Y(n141));
  NOR4X1   g098(.A(n141), .B(n94), .C(G79gat), .D(n138), .Y(n142));
  XOR2X1   g099(.A(G223gat), .B(n98), .Y(n143));
  NAND3X1  g100(.A(n143), .B(n100), .C(G56gat), .Y(n144));
  XOR2X1   g101(.A(n114), .B(n144), .Y(n145));
  INVX1    g102(.A(G66gat), .Y(n146));
  NAND3X1  g103(.A(n143), .B(n146), .C(G56gat), .Y(n147));
  XOR2X1   g104(.A(G223gat), .B(n102), .Y(n148));
  NAND3X1  g105(.A(n148), .B(n104), .C(G43gat), .Y(n149));
  XOR2X1   g106(.A(n114), .B(n149), .Y(n150));
  INVX1    g107(.A(G53gat), .Y(n151));
  NAND3X1  g108(.A(n148), .B(n151), .C(G43gat), .Y(n152));
  OAI22X1  g109(.A0(n152), .A1(n150), .B0(n147), .B1(n145), .Y(n153));
  NOR3X1   g110(.A(n153), .B(n142), .C(n137), .Y(n154));
  NAND4X1  g111(.A(n154), .B(n131), .C(n122), .D(n111), .Y(G370gat));
  INVX1    g112(.A(G8gat), .Y(n156));
  AOI21X1  g113(.A0(G223gat), .A1(G1gat), .B0(n46), .Y(n157));
  OAI21X1  g114(.A0(n114), .A1(n156), .B0(n157), .Y(n158));
  AOI21X1  g115(.A0(G370gat), .A1(G14gat), .B0(n158), .Y(n159));
  OAI21X1  g116(.A0(n82), .A1(n54), .B0(G82gat), .Y(n160));
  AOI21X1  g117(.A0(G329gat), .A1(G86gat), .B0(n160), .Y(n161));
  INVX1    g118(.A(n161), .Y(n162));
  AOI21X1  g119(.A0(G370gat), .A1(G92gat), .B0(n162), .Y(n163));
  AOI21X1  g120(.A0(G223gat), .A1(G63gat), .B0(n138), .Y(n164));
  OAI21X1  g121(.A0(n114), .A1(n95), .B0(n164), .Y(n165));
  AOI21X1  g122(.A0(G370gat), .A1(G79gat), .B0(n165), .Y(n166));
  OAI21X1  g123(.A0(n82), .A1(n59), .B0(G56gat), .Y(n167));
  AOI21X1  g124(.A0(G329gat), .A1(G60gat), .B0(n167), .Y(n168));
  INVX1    g125(.A(n168), .Y(n169));
  AOI21X1  g126(.A0(G370gat), .A1(G66gat), .B0(n169), .Y(n170));
  OAI21X1  g127(.A0(n82), .A1(n58), .B0(G43gat), .Y(n171));
  AOI21X1  g128(.A0(G329gat), .A1(G47gat), .B0(n171), .Y(n172));
  INVX1    g129(.A(n172), .Y(n173));
  AOI21X1  g130(.A0(G370gat), .A1(G53gat), .B0(n173), .Y(n174));
  NOR4X1   g131(.A(n174), .B(n170), .C(n166), .D(n163), .Y(n175));
  AOI21X1  g132(.A0(G223gat), .A1(G11gat), .B0(n44), .Y(n176));
  OAI21X1  g133(.A0(n114), .A1(n64), .B0(n176), .Y(n177));
  AOI21X1  g134(.A0(G370gat), .A1(G27gat), .B0(n177), .Y(n178));
  INVX1    g135(.A(G24gat), .Y(n179));
  OAI21X1  g136(.A0(n82), .A1(n179), .B0(G30gat), .Y(n180));
  AOI21X1  g137(.A0(G329gat), .A1(G34gat), .B0(n180), .Y(n181));
  INVX1    g138(.A(n181), .Y(n182));
  AOI21X1  g139(.A0(G370gat), .A1(G40gat), .B0(n182), .Y(n183));
  AOI21X1  g140(.A0(G223gat), .A1(G102gat), .B0(n50), .Y(n184));
  OAI21X1  g141(.A0(n114), .A1(n84), .B0(n184), .Y(n185));
  AOI21X1  g142(.A0(G370gat), .A1(G115gat), .B0(n185), .Y(n186));
  AOI21X1  g143(.A0(G223gat), .A1(G89gat), .B0(n52), .Y(n187));
  OAI21X1  g144(.A0(n114), .A1(n88), .B0(n187), .Y(n188));
  AOI21X1  g145(.A0(G370gat), .A1(G105gat), .B0(n188), .Y(n189));
  NOR4X1   g146(.A(n189), .B(n186), .C(n183), .D(n178), .Y(n190));
  AOI21X1  g147(.A0(n190), .A1(n175), .B0(n159), .Y(G421gat));
  XOR2X1   g148(.A(n114), .B(n70), .Y(n192));
  NAND3X1  g149(.A(n69), .B(n109), .C(G17gat), .Y(n193));
  OAI21X1  g150(.A0(n193), .A1(n192), .B0(n122), .Y(n194));
  NOR2X1   g151(.A(G115gat), .B(n50), .Y(n195));
  NAND2X1  g152(.A(n195), .B(n123), .Y(n196));
  NOR2X1   g153(.A(G105gat), .B(n52), .Y(n197));
  NAND2X1  g154(.A(n197), .B(n127), .Y(n198));
  OAI22X1  g155(.A0(n198), .A1(n129), .B0(n196), .B1(n125), .Y(n199));
  NOR2X1   g156(.A(n93), .B(n91), .Y(n200));
  XOR2X1   g157(.A(n114), .B(n200), .Y(n201));
  NAND4X1  g158(.A(n201), .B(n132), .C(n135), .D(G82gat), .Y(n202));
  NOR2X1   g159(.A(n96), .B(n94), .Y(n203));
  XOR2X1   g160(.A(n114), .B(n203), .Y(n204));
  NOR3X1   g161(.A(n94), .B(G79gat), .C(n138), .Y(n205));
  NAND2X1  g162(.A(n205), .B(n204), .Y(n206));
  NAND2X1  g163(.A(n206), .B(n202), .Y(n207));
  NOR4X1   g164(.A(n153), .B(n207), .C(n199), .D(n194), .Y(n208));
  OAI21X1  g165(.A0(n208), .A1(n146), .B0(n168), .Y(n209));
  OAI21X1  g166(.A0(n208), .A1(n151), .B0(n172), .Y(n210));
  INVX1    g167(.A(n177), .Y(n211));
  OAI21X1  g168(.A0(n208), .A1(n109), .B0(n211), .Y(n212));
  INVX1    g169(.A(G40gat), .Y(n213));
  OAI21X1  g170(.A0(n208), .A1(n213), .B0(n181), .Y(n214));
  NAND4X1  g171(.A(n214), .B(n212), .C(n210), .D(n209), .Y(G430gat));
  NAND3X1  g172(.A(n210), .B(n209), .C(n163), .Y(n216));
  NAND4X1  g173(.A(n214), .B(n210), .C(n209), .D(n166), .Y(n217));
  NAND4X1  g174(.A(n217), .B(n216), .C(n214), .D(n212), .Y(G431gat));
  OAI21X1  g175(.A0(n208), .A1(n135), .B0(n161), .Y(n219));
  NAND4X1  g176(.A(n189), .B(n214), .C(n210), .D(n219), .Y(n220));
  AOI21X1  g177(.A0(n214), .A1(n174), .B0(n178), .Y(n221));
  NAND3X1  g178(.A(n221), .B(n220), .C(n217), .Y(G432gat));
endmodule


