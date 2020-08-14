// Benchmark "c432.blif" written by ABC on Tue Aug 08 01:56:34 2017

module c432.blif ( 
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
  wire n43, n44, n45, n46, n47, n48, n49, n50, n51, n52, n53, n54, n55, n56,
    n57, n58, n59, n61, n62, n63, n64, n65, n66, n67, n70, n71, n72, n75,
    n76, n77, n78, n79, n80, n81, n82, n83, n84, n85, n86, n87, n88, n89,
    n90, n91, n92, n93, n94, n95, n96, n97, n99, n100, n101, n102, n103,
    n104, n105, n106, n107, n108, n109, n110, n111, n112, n113, n114, n115,
    n116, n117, n118, n119, n120, n121, n122, n123, n124, n125, n126, n127,
    n128, n129, n131, n132, n133, n134, n135, n136, n137, n138, n139, n140,
    n141, n142, n143, n144, n145, n146, n147, n148, n149, n150, n151, n152,
    n153, n154, n155, n156, n157, n158, n159, n160, n161, n162, n163, n164,
    n165, n166, n169, n170, n171, n172, n173, n174, n176, n177, n178, n179,
    n180, n181;
  INVX1    g000(.A(G108gat), .Y(n43));
  NOR2X1   g001(.A(n43), .B(G102gat), .Y(n44));
  INVX1    g002(.A(G89gat), .Y(n45));
  AND2X1   g003(.A(G95gat), .B(n45), .Y(n46));
  INVX1    g004(.A(G76gat), .Y(n47));
  AND2X1   g005(.A(G82gat), .B(n47), .Y(n48));
  INVX1    g006(.A(G4gat), .Y(n49));
  INVX1    g007(.A(G17gat), .Y(n50));
  OAI22X1  g008(.A0(n50), .A1(G11gat), .B0(n49), .B1(G1gat), .Y(n51));
  INVX1    g009(.A(G43gat), .Y(n52));
  NOR2X1   g010(.A(n52), .B(G37gat), .Y(n53));
  INVX1    g011(.A(G30gat), .Y(n54));
  NOR2X1   g012(.A(n54), .B(G24gat), .Y(n55));
  INVX1    g013(.A(G56gat), .Y(n56));
  INVX1    g014(.A(G69gat), .Y(n57));
  OAI22X1  g015(.A0(n57), .A1(G63gat), .B0(n56), .B1(G50gat), .Y(n58));
  OR4X1    g016(.A(n58), .B(n55), .C(n53), .D(n51), .Y(n59));
  OR4X1    g017(.A(n59), .B(n48), .C(n46), .D(n44), .Y(G223gat));
  INVX1    g018(.A(G112gat), .Y(n61));
  INVX1    g019(.A(G82gat), .Y(n62));
  INVX1    g020(.A(G95gat), .Y(n63));
  OAI22X1  g021(.A0(n63), .A1(G89gat), .B0(n62), .B1(G76gat), .Y(n64));
  OAI22X1  g022(.A0(n59), .A1(n64), .B0(n43), .B1(G102gat), .Y(n65));
  NAND3X1  g023(.A(n65), .B(n61), .C(G108gat), .Y(n66));
  NOR4X1   g024(.A(n59), .B(n48), .C(n46), .D(n44), .Y(n67));
  INVX1    g025(.A(G99gat), .Y(n70));
  AND2X1   g026(.A(n70), .B(G95gat), .Y(n71));
  OAI21X1  g027(.A0(n46), .A1(n67), .B0(n71), .Y(n72));
  INVX1    g028(.A(G86gat), .Y(n75));
  AND2X1   g029(.A(n75), .B(G82gat), .Y(n76));
  OAI21X1  g030(.A0(n48), .A1(n67), .B0(n76), .Y(n77));
  NAND3X1  g031(.A(n77), .B(n72), .C(n66), .Y(n78));
  NOR2X1   g032(.A(n49), .B(G1gat), .Y(n79));
  XOR2X1   g033(.A(G223gat), .B(n79), .Y(n80));
  OR2X1    g034(.A(G8gat), .B(n49), .Y(n81));
  NOR2X1   g035(.A(n50), .B(G11gat), .Y(n82));
  XOR2X1   g036(.A(G223gat), .B(n82), .Y(n83));
  OR2X1    g037(.A(G21gat), .B(n50), .Y(n84));
  OAI22X1  g038(.A0(n84), .A1(n83), .B0(n81), .B1(n80), .Y(n85));
  XOR2X1   g039(.A(G223gat), .B(n53), .Y(n86));
  OR2X1    g040(.A(G47gat), .B(n52), .Y(n87));
  XOR2X1   g041(.A(G223gat), .B(n55), .Y(n88));
  OR2X1    g042(.A(G34gat), .B(n54), .Y(n89));
  OAI22X1  g043(.A0(n89), .A1(n88), .B0(n87), .B1(n86), .Y(n90));
  NOR2X1   g044(.A(n57), .B(G63gat), .Y(n91));
  XOR2X1   g045(.A(G223gat), .B(n91), .Y(n92));
  OR2X1    g046(.A(G73gat), .B(n57), .Y(n93));
  NOR2X1   g047(.A(n56), .B(G50gat), .Y(n94));
  XOR2X1   g048(.A(G223gat), .B(n94), .Y(n95));
  OR2X1    g049(.A(G60gat), .B(n56), .Y(n96));
  OAI22X1  g050(.A0(n96), .A1(n95), .B0(n93), .B1(n92), .Y(n97));
  OR4X1    g051(.A(n97), .B(n90), .C(n85), .D(n78), .Y(G329gat));
  NOR4X1   g052(.A(n97), .B(n90), .C(n85), .D(n78), .Y(n99));
  XOR2X1   g053(.A(n99), .B(n66), .Y(n100));
  INVX1    g054(.A(G115gat), .Y(n101));
  NAND3X1  g055(.A(n65), .B(n101), .C(G108gat), .Y(n102));
  XOR2X1   g056(.A(G329gat), .B(n72), .Y(n103));
  XOR2X1   g057(.A(G223gat), .B(n46), .Y(n104));
  NOR3X1   g058(.A(n104), .B(G105gat), .C(n63), .Y(n105));
  XOR2X1   g059(.A(G329gat), .B(n77), .Y(n106));
  XOR2X1   g060(.A(G223gat), .B(n48), .Y(n107));
  NOR3X1   g061(.A(n107), .B(G92gat), .C(n62), .Y(n108));
  AOI22X1  g062(.A0(n108), .A1(n106), .B0(n105), .B1(n103), .Y(n109));
  OAI21X1  g063(.A0(n102), .A1(n100), .B0(n109), .Y(n110));
  NOR2X1   g064(.A(n81), .B(n80), .Y(n111));
  XOR2X1   g065(.A(G329gat), .B(n111), .Y(n112));
  NOR4X1   g066(.A(n112), .B(n80), .C(G14gat), .D(n49), .Y(n113));
  NOR2X1   g067(.A(n84), .B(n83), .Y(n114));
  XOR2X1   g068(.A(G329gat), .B(n114), .Y(n115));
  NOR4X1   g069(.A(n115), .B(n83), .C(G27gat), .D(n50), .Y(n116));
  OR2X1    g070(.A(n87), .B(n86), .Y(n117));
  XOR2X1   g071(.A(n99), .B(n117), .Y(n118));
  NOR4X1   g072(.A(n118), .B(n86), .C(G53gat), .D(n52), .Y(n119));
  OR2X1    g073(.A(n89), .B(n88), .Y(n120));
  XOR2X1   g074(.A(n99), .B(n120), .Y(n121));
  NOR4X1   g075(.A(n121), .B(n88), .C(G40gat), .D(n54), .Y(n122));
  OR2X1    g076(.A(n93), .B(n92), .Y(n123));
  XOR2X1   g077(.A(n99), .B(n123), .Y(n124));
  NOR4X1   g078(.A(n124), .B(n92), .C(G79gat), .D(n57), .Y(n125));
  OR2X1    g079(.A(n96), .B(n95), .Y(n126));
  XOR2X1   g080(.A(n99), .B(n126), .Y(n127));
  NOR4X1   g081(.A(n127), .B(n95), .C(G66gat), .D(n56), .Y(n128));
  OR4X1    g082(.A(n128), .B(n125), .C(n122), .D(n119), .Y(n129));
  OR4X1    g083(.A(n129), .B(n116), .C(n113), .D(n110), .Y(G370gat));
  INVX1    g084(.A(G8gat), .Y(n131));
  AOI21X1  g085(.A0(G223gat), .A1(G1gat), .B0(n49), .Y(n132));
  OAI21X1  g086(.A0(n99), .A1(n131), .B0(n132), .Y(n133));
  AOI21X1  g087(.A0(G370gat), .A1(G14gat), .B0(n133), .Y(n134));
  AOI21X1  g088(.A0(G223gat), .A1(G76gat), .B0(n62), .Y(n135));
  OAI21X1  g089(.A0(n99), .A1(n75), .B0(n135), .Y(n136));
  AOI21X1  g090(.A0(G370gat), .A1(G92gat), .B0(n136), .Y(n137));
  AOI21X1  g091(.A0(G223gat), .A1(G63gat), .B0(n57), .Y(n138));
  INVX1    g092(.A(n138), .Y(n139));
  AOI21X1  g093(.A0(G329gat), .A1(G73gat), .B0(n139), .Y(n140));
  INVX1    g094(.A(n140), .Y(n141));
  AOI21X1  g095(.A0(G370gat), .A1(G79gat), .B0(n141), .Y(n142));
  AOI21X1  g096(.A0(G223gat), .A1(G102gat), .B0(n43), .Y(n143));
  OAI21X1  g097(.A0(n99), .A1(n61), .B0(n143), .Y(n144));
  AOI21X1  g098(.A0(G370gat), .A1(G115gat), .B0(n144), .Y(n145));
  AOI21X1  g099(.A0(G223gat), .A1(G89gat), .B0(n63), .Y(n146));
  OAI21X1  g100(.A0(n99), .A1(n70), .B0(n146), .Y(n147));
  AOI21X1  g101(.A0(G370gat), .A1(G105gat), .B0(n147), .Y(n148));
  NOR4X1   g102(.A(n148), .B(n145), .C(n142), .D(n137), .Y(n149));
  INVX1    g103(.A(G21gat), .Y(n150));
  AOI21X1  g104(.A0(G223gat), .A1(G11gat), .B0(n50), .Y(n151));
  OAI21X1  g105(.A0(n99), .A1(n150), .B0(n151), .Y(n152));
  AOI21X1  g106(.A0(G370gat), .A1(G27gat), .B0(n152), .Y(n153));
  INVX1    g107(.A(G34gat), .Y(n154));
  AOI21X1  g108(.A0(G223gat), .A1(G24gat), .B0(n54), .Y(n155));
  OAI21X1  g109(.A0(n99), .A1(n154), .B0(n155), .Y(n156));
  AOI21X1  g110(.A0(G370gat), .A1(G40gat), .B0(n156), .Y(n157));
  INVX1    g111(.A(G60gat), .Y(n158));
  AOI21X1  g112(.A0(G223gat), .A1(G50gat), .B0(n56), .Y(n159));
  OAI21X1  g113(.A0(n99), .A1(n158), .B0(n159), .Y(n160));
  AOI21X1  g114(.A0(G370gat), .A1(G66gat), .B0(n160), .Y(n161));
  INVX1    g115(.A(G47gat), .Y(n162));
  AOI21X1  g116(.A0(G223gat), .A1(G37gat), .B0(n52), .Y(n163));
  OAI21X1  g117(.A0(n99), .A1(n162), .B0(n163), .Y(n164));
  AOI21X1  g118(.A0(G370gat), .A1(G53gat), .B0(n164), .Y(n165));
  NOR4X1   g119(.A(n165), .B(n161), .C(n157), .D(n153), .Y(n166));
  AOI21X1  g120(.A0(n166), .A1(n149), .B0(n134), .Y(G421gat));
  OR4X1    g121(.A(n165), .B(n161), .C(n157), .D(n153), .Y(G430gat));
  INVX1    g122(.A(G79gat), .Y(n169));
  NOR4X1   g123(.A(n129), .B(n116), .C(n113), .D(n110), .Y(n170));
  OAI21X1  g124(.A0(n170), .A1(n169), .B0(n140), .Y(n171));
  NOR4X1   g125(.A(n165), .B(n161), .C(n157), .D(n171), .Y(n172));
  AND2X1   g126(.A(G370gat), .B(G92gat), .Y(n173));
  NOR4X1   g127(.A(n165), .B(n161), .C(n136), .D(n173), .Y(n174));
  OR4X1    g128(.A(n174), .B(n172), .C(n157), .D(n153), .Y(G431gat));
  AND2X1   g129(.A(G370gat), .B(G53gat), .Y(n176));
  NOR3X1   g130(.A(n164), .B(n176), .C(n157), .Y(n177));
  INVX1    g131(.A(G105gat), .Y(n178));
  INVX1    g132(.A(n147), .Y(n179));
  OAI21X1  g133(.A0(n170), .A1(n178), .B0(n179), .Y(n180));
  NOR4X1   g134(.A(n165), .B(n157), .C(n180), .D(n137), .Y(n181));
  OR4X1    g135(.A(n181), .B(n172), .C(n177), .D(n153), .Y(G432gat));
endmodule


