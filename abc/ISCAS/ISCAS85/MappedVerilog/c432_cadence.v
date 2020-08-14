// Benchmark "c432.blif" written by ABC on Fri Sep  1 22:12:10 2017

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
    n58, n59, n60, n62, n63, n64, n65, n66, n67, n68, n70, n71, n72, n73,
    n74, n75, n76, n77, n78, n79, n80, n81, n82, n83, n84, n85, n86, n87,
    n88, n89, n90, n91, n92, n93, n94, n95, n96, n97, n98, n100, n101,
    n102, n103, n104, n105, n106, n107, n108, n109, n110, n111, n112, n113,
    n114, n115, n116, n117, n118, n119, n120, n121, n122, n123, n124, n125,
    n126, n127, n128, n129, n130, n132, n133, n134, n135, n136, n137, n138,
    n139, n140, n141, n142, n143, n144, n145, n146, n147, n148, n149, n150,
    n151, n152, n153, n154, n155, n156, n157, n158, n159, n160, n161, n162,
    n163, n164, n165, n166, n167, n168, n171, n172, n173, n174, n175, n176,
    n178, n179, n180, n181, n182;
  INVX1    g000(.A(G17gat), .Y(n44));
  NOR2X1   g001(.A(n44), .B(G11gat), .Y(n45));
  INVX1    g002(.A(G24gat), .Y(n46));
  AND2X1   g003(.A(G30gat), .B(n46), .Y(n47));
  INVX1    g004(.A(G1gat), .Y(n48));
  AND2X1   g005(.A(G4gat), .B(n48), .Y(n49));
  INVX1    g006(.A(G95gat), .Y(n50));
  INVX1    g007(.A(G108gat), .Y(n51));
  OAI22X1  g008(.A0(n51), .A1(G102gat), .B0(n50), .B1(G89gat), .Y(n52));
  INVX1    g009(.A(G82gat), .Y(n53));
  NOR2X1   g010(.A(n53), .B(G76gat), .Y(n54));
  INVX1    g011(.A(G69gat), .Y(n55));
  NOR2X1   g012(.A(n55), .B(G63gat), .Y(n56));
  INVX1    g013(.A(G43gat), .Y(n57));
  INVX1    g014(.A(G56gat), .Y(n58));
  OAI22X1  g015(.A0(n58), .A1(G50gat), .B0(n57), .B1(G37gat), .Y(n59));
  OR4X1    g016(.A(n59), .B(n56), .C(n54), .D(n52), .Y(n60));
  OR4X1    g017(.A(n60), .B(n49), .C(n47), .D(n45), .Y(G223gat));
  INVX1    g018(.A(G21gat), .Y(n62));
  INVX1    g019(.A(G4gat), .Y(n63));
  INVX1    g020(.A(G30gat), .Y(n64));
  OAI22X1  g021(.A0(n64), .A1(G24gat), .B0(n63), .B1(G1gat), .Y(n65));
  OAI22X1  g022(.A0(n60), .A1(n65), .B0(n44), .B1(G11gat), .Y(n66));
  NAND3X1  g023(.A(n66), .B(n62), .C(G17gat), .Y(n67));
  NOR4X1   g024(.A(n60), .B(n49), .C(n47), .D(n45), .Y(n68));
  AND2X1   g025(.A(G30gat), .B(n46), .Y(n70));
  INVX1    g026(.A(G34gat), .Y(n71));
  AND2X1   g027(.A(n71), .B(G30gat), .Y(n72));
  OAI21X1  g028(.A0(n70), .A1(n68), .B0(n72), .Y(n73));
  NOR4X1   g029(.A(n60), .B(n49), .C(n47), .D(n45), .Y(n74));
  AND2X1   g030(.A(G4gat), .B(n48), .Y(n75));
  INVX1    g031(.A(G8gat), .Y(n76));
  AND2X1   g032(.A(n76), .B(G4gat), .Y(n77));
  OAI21X1  g033(.A0(n75), .A1(n74), .B0(n77), .Y(n78));
  NAND3X1  g034(.A(n78), .B(n73), .C(n67), .Y(n79));
  NOR2X1   g035(.A(n51), .B(G102gat), .Y(n80));
  XOR2X1   g036(.A(G223gat), .B(n80), .Y(n81));
  OR2X1    g037(.A(G112gat), .B(n51), .Y(n82));
  NOR2X1   g038(.A(n50), .B(G89gat), .Y(n83));
  XOR2X1   g039(.A(G223gat), .B(n83), .Y(n84));
  OR2X1    g040(.A(G99gat), .B(n50), .Y(n85));
  OAI22X1  g041(.A0(n85), .A1(n84), .B0(n82), .B1(n81), .Y(n86));
  XOR2X1   g042(.A(G223gat), .B(n54), .Y(n87));
  OR2X1    g043(.A(G86gat), .B(n53), .Y(n88));
  XOR2X1   g044(.A(G223gat), .B(n56), .Y(n89));
  OR2X1    g045(.A(G73gat), .B(n55), .Y(n90));
  OAI22X1  g046(.A0(n90), .A1(n89), .B0(n88), .B1(n87), .Y(n91));
  NOR2X1   g047(.A(n58), .B(G50gat), .Y(n92));
  XOR2X1   g048(.A(G223gat), .B(n92), .Y(n93));
  OR2X1    g049(.A(G60gat), .B(n58), .Y(n94));
  NOR2X1   g050(.A(n57), .B(G37gat), .Y(n95));
  XOR2X1   g051(.A(G223gat), .B(n95), .Y(n96));
  OR2X1    g052(.A(G47gat), .B(n57), .Y(n97));
  OAI22X1  g053(.A0(n97), .A1(n96), .B0(n94), .B1(n93), .Y(n98));
  OR4X1    g054(.A(n98), .B(n91), .C(n86), .D(n79), .Y(G329gat));
  NOR4X1   g055(.A(n98), .B(n91), .C(n86), .D(n79), .Y(n100));
  XOR2X1   g056(.A(n100), .B(n67), .Y(n101));
  INVX1    g057(.A(G27gat), .Y(n102));
  NAND3X1  g058(.A(n66), .B(n102), .C(G17gat), .Y(n103));
  XOR2X1   g059(.A(G329gat), .B(n73), .Y(n104));
  XOR2X1   g060(.A(G223gat), .B(n47), .Y(n105));
  NOR3X1   g061(.A(n105), .B(G40gat), .C(n64), .Y(n106));
  XOR2X1   g062(.A(G329gat), .B(n78), .Y(n107));
  XOR2X1   g063(.A(G223gat), .B(n49), .Y(n108));
  NOR3X1   g064(.A(n108), .B(G14gat), .C(n63), .Y(n109));
  AOI22X1  g065(.A0(n109), .A1(n107), .B0(n106), .B1(n104), .Y(n110));
  OAI21X1  g066(.A0(n103), .A1(n101), .B0(n110), .Y(n111));
  NOR2X1   g067(.A(n82), .B(n81), .Y(n112));
  XOR2X1   g068(.A(G329gat), .B(n112), .Y(n113));
  NOR4X1   g069(.A(n113), .B(n81), .C(G115gat), .D(n51), .Y(n114));
  NOR2X1   g070(.A(n85), .B(n84), .Y(n115));
  XOR2X1   g071(.A(G329gat), .B(n115), .Y(n116));
  NOR4X1   g072(.A(n116), .B(n84), .C(G105gat), .D(n50), .Y(n117));
  OR2X1    g073(.A(n88), .B(n87), .Y(n118));
  XOR2X1   g074(.A(n100), .B(n118), .Y(n119));
  NOR4X1   g075(.A(n119), .B(n87), .C(G92gat), .D(n53), .Y(n120));
  OR2X1    g076(.A(n90), .B(n89), .Y(n121));
  XOR2X1   g077(.A(n100), .B(n121), .Y(n122));
  NOR4X1   g078(.A(n122), .B(n89), .C(G79gat), .D(n55), .Y(n123));
  OR2X1    g079(.A(n94), .B(n93), .Y(n124));
  XOR2X1   g080(.A(n100), .B(n124), .Y(n125));
  NOR4X1   g081(.A(n125), .B(n93), .C(G66gat), .D(n58), .Y(n126));
  OR2X1    g082(.A(n97), .B(n96), .Y(n127));
  XOR2X1   g083(.A(n100), .B(n127), .Y(n128));
  NOR4X1   g084(.A(n128), .B(n96), .C(G53gat), .D(n57), .Y(n129));
  OR4X1    g085(.A(n129), .B(n126), .C(n123), .D(n120), .Y(n130));
  OR4X1    g086(.A(n130), .B(n117), .C(n114), .D(n111), .Y(G370gat));
  AOI21X1  g087(.A0(G223gat), .A1(G1gat), .B0(n63), .Y(n132));
  OAI21X1  g088(.A0(n100), .A1(n76), .B0(n132), .Y(n133));
  AOI21X1  g089(.A0(G370gat), .A1(G14gat), .B0(n133), .Y(n134));
  INVX1    g090(.A(G86gat), .Y(n135));
  AOI21X1  g091(.A0(G223gat), .A1(G76gat), .B0(n53), .Y(n136));
  OAI21X1  g092(.A0(n100), .A1(n135), .B0(n136), .Y(n137));
  AOI21X1  g093(.A0(G370gat), .A1(G92gat), .B0(n137), .Y(n138));
  AOI21X1  g094(.A0(G223gat), .A1(G63gat), .B0(n55), .Y(n139));
  INVX1    g095(.A(n139), .Y(n140));
  AOI21X1  g096(.A0(G329gat), .A1(G73gat), .B0(n140), .Y(n141));
  INVX1    g097(.A(n141), .Y(n142));
  AOI21X1  g098(.A0(G370gat), .A1(G79gat), .B0(n142), .Y(n143));
  INVX1    g099(.A(G60gat), .Y(n144));
  AOI21X1  g100(.A0(G223gat), .A1(G50gat), .B0(n58), .Y(n145));
  OAI21X1  g101(.A0(n100), .A1(n144), .B0(n145), .Y(n146));
  AOI21X1  g102(.A0(G370gat), .A1(G66gat), .B0(n146), .Y(n147));
  INVX1    g103(.A(G47gat), .Y(n148));
  AOI21X1  g104(.A0(G223gat), .A1(G37gat), .B0(n57), .Y(n149));
  OAI21X1  g105(.A0(n100), .A1(n148), .B0(n149), .Y(n150));
  AOI21X1  g106(.A0(G370gat), .A1(G53gat), .B0(n150), .Y(n151));
  NOR4X1   g107(.A(n151), .B(n147), .C(n143), .D(n138), .Y(n152));
  AOI21X1  g108(.A0(G223gat), .A1(G11gat), .B0(n44), .Y(n153));
  OAI21X1  g109(.A0(n100), .A1(n62), .B0(n153), .Y(n154));
  AOI21X1  g110(.A0(G370gat), .A1(G27gat), .B0(n154), .Y(n155));
  AOI21X1  g111(.A0(G223gat), .A1(G24gat), .B0(n64), .Y(n156));
  OAI21X1  g112(.A0(n100), .A1(n71), .B0(n156), .Y(n157));
  AOI21X1  g113(.A0(G370gat), .A1(G40gat), .B0(n157), .Y(n158));
  INVX1    g114(.A(G112gat), .Y(n159));
  AOI21X1  g115(.A0(G223gat), .A1(G102gat), .B0(n51), .Y(n160));
  OAI21X1  g116(.A0(n100), .A1(n159), .B0(n160), .Y(n161));
  AOI21X1  g117(.A0(G370gat), .A1(G115gat), .B0(n161), .Y(n162));
  AOI21X1  g118(.A0(G223gat), .A1(G89gat), .B0(n50), .Y(n163));
  INVX1    g119(.A(n163), .Y(n164));
  AOI21X1  g120(.A0(G329gat), .A1(G99gat), .B0(n164), .Y(n165));
  INVX1    g121(.A(n165), .Y(n166));
  AOI21X1  g122(.A0(G370gat), .A1(G105gat), .B0(n166), .Y(n167));
  NOR4X1   g123(.A(n167), .B(n162), .C(n158), .D(n155), .Y(n168));
  AOI21X1  g124(.A0(n168), .A1(n152), .B0(n134), .Y(G421gat));
  OR4X1    g125(.A(n158), .B(n155), .C(n151), .D(n147), .Y(G430gat));
  AND2X1   g126(.A(G370gat), .B(G92gat), .Y(n171));
  NOR4X1   g127(.A(n151), .B(n147), .C(n137), .D(n171), .Y(n172));
  INVX1    g128(.A(G79gat), .Y(n173));
  NOR4X1   g129(.A(n130), .B(n117), .C(n114), .D(n111), .Y(n174));
  OAI21X1  g130(.A0(n174), .A1(n173), .B0(n141), .Y(n175));
  NOR4X1   g131(.A(n158), .B(n151), .C(n147), .D(n175), .Y(n176));
  OR4X1    g132(.A(n176), .B(n172), .C(n158), .D(n155), .Y(G431gat));
  AND2X1   g133(.A(G370gat), .B(G53gat), .Y(n178));
  NOR3X1   g134(.A(n158), .B(n150), .C(n178), .Y(n179));
  INVX1    g135(.A(G105gat), .Y(n180));
  OAI21X1  g136(.A0(n174), .A1(n180), .B0(n165), .Y(n181));
  NOR4X1   g137(.A(n181), .B(n158), .C(n151), .D(n138), .Y(n182));
  OR4X1    g138(.A(n182), .B(n176), .C(n179), .D(n155), .Y(G432gat));
endmodule


