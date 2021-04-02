// Benchmark "c432.blif" written by ABC on Wed Mar 31 18:38:49 2021

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
  wire new_n44_, new_n45_, new_n46_, new_n47_, new_n48_, new_n49_, new_n50_,
    new_n51_, new_n52_, new_n53_, new_n54_, new_n55_, new_n56_, new_n57_,
    new_n58_, new_n59_, new_n60_, new_n62_, new_n63_, new_n64_, new_n65_,
    new_n66_, new_n67_, new_n68_, new_n69_, new_n70_, new_n71_, new_n72_,
    new_n73_, new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_,
    new_n80_, new_n81_, new_n82_, new_n83_, new_n84_, new_n85_, new_n86_,
    new_n87_, new_n88_, new_n89_, new_n90_, new_n91_, new_n92_, new_n93_,
    new_n94_, new_n95_, new_n96_, new_n97_, new_n98_, new_n99_, new_n100_,
    new_n101_, new_n102_, new_n103_, new_n104_, new_n105_, new_n106_,
    new_n107_, new_n108_, new_n109_, new_n110_, new_n111_, new_n113_,
    new_n114_, new_n115_, new_n116_, new_n117_, new_n118_, new_n119_,
    new_n120_, new_n121_, new_n122_, new_n123_, new_n124_, new_n125_,
    new_n126_, new_n127_, new_n128_, new_n129_, new_n130_, new_n131_,
    new_n132_, new_n133_, new_n134_, new_n135_, new_n136_, new_n138_,
    new_n139_, new_n140_, new_n141_, new_n142_, new_n143_, new_n144_,
    new_n145_, new_n146_, new_n147_, new_n148_, new_n149_, new_n150_,
    new_n151_, new_n152_, new_n153_, new_n154_, new_n155_, new_n156_,
    new_n157_, new_n158_, new_n159_, new_n160_, new_n161_, new_n162_,
    new_n163_, new_n164_, new_n165_, new_n166_, new_n167_, new_n168_,
    new_n169_, new_n170_, new_n171_, new_n172_, new_n173_, new_n175_,
    new_n176_, new_n177_, new_n178_, new_n179_, new_n180_, new_n181_,
    new_n182_, new_n183_, new_n184_, new_n185_, new_n186_, new_n187_,
    new_n188_, new_n189_, new_n190_, new_n191_, new_n192_, new_n193_,
    new_n194_, new_n195_, new_n196_, new_n197_, new_n198_, new_n199_,
    new_n201_, new_n202_, new_n203_, new_n204_, new_n205_, new_n206_,
    new_n207_, new_n208_, new_n209_, new_n210_, new_n211_, new_n212_,
    new_n213_, new_n214_, new_n215_, new_n216_, new_n217_, new_n218_,
    new_n219_, new_n220_, new_n221_, new_n222_, new_n223_, new_n224_,
    new_n225_, new_n226_, new_n227_, new_n228_, new_n229_, new_n230_,
    new_n231_, new_n233_, new_n234_, new_n236_, new_n237_, new_n238_,
    new_n239_;
  INV    g000(.A(G17gat), .Y(new_n44_));
  NOR2   g001(.A(new_n44_), .B(G11gat), .Y(new_n45_));
  INV    g002(.A(G4gat), .Y(new_n46_));
  INV    g003(.A(G30gat), .Y(new_n47_));
  OAI22  g004(.A0(new_n47_), .A1(G24gat), .B0(new_n46_), .B1(G1gat), .Y(new_n48_));
  NOR2   g005(.A(new_n48_), .B(new_n45_), .Y(new_n49_));
  INV    g006(.A(G95gat), .Y(new_n50_));
  INV    g007(.A(G108gat), .Y(new_n51_));
  OAI22  g008(.A0(new_n51_), .A1(G102gat), .B0(new_n50_), .B1(G89gat), .Y(new_n52_));
  INV    g009(.A(G82gat), .Y(new_n53_));
  NOR2   g010(.A(new_n53_), .B(G76gat), .Y(new_n54_));
  INV    g011(.A(G69gat), .Y(new_n55_));
  NOR2   g012(.A(new_n55_), .B(G63gat), .Y(new_n56_));
  INV    g013(.A(G43gat), .Y(new_n57_));
  INV    g014(.A(G56gat), .Y(new_n58_));
  OAI22  g015(.A0(new_n58_), .A1(G50gat), .B0(new_n57_), .B1(G37gat), .Y(new_n59_));
  NOR4   g016(.A(new_n59_), .B(new_n56_), .C(new_n54_), .D(new_n52_), .Y(new_n60_));
  NAND2  g017(.A(new_n60_), .B(new_n49_), .Y(G223gat));
  INV    g018(.A(G21gat), .Y(new_n62_));
  INV    g019(.A(G89gat), .Y(new_n63_));
  INV    g020(.A(G102gat), .Y(new_n64_));
  AOI22  g021(.A0(G108gat), .A1(new_n64_), .B0(G95gat), .B1(new_n63_), .Y(new_n65_));
  INV    g022(.A(G76gat), .Y(new_n66_));
  NAND2  g023(.A(G82gat), .B(new_n66_), .Y(new_n67_));
  INV    g024(.A(G63gat), .Y(new_n68_));
  NAND2  g025(.A(G69gat), .B(new_n68_), .Y(new_n69_));
  INV    g026(.A(G37gat), .Y(new_n70_));
  INV    g027(.A(G50gat), .Y(new_n71_));
  AOI22  g028(.A0(G56gat), .A1(new_n71_), .B0(G43gat), .B1(new_n70_), .Y(new_n72_));
  NAND4  g029(.A(new_n72_), .B(new_n69_), .C(new_n67_), .D(new_n65_), .Y(new_n73_));
  OAI22  g030(.A0(new_n73_), .A1(new_n48_), .B0(new_n44_), .B1(G11gat), .Y(new_n74_));
  NAND3  g031(.A(new_n74_), .B(new_n62_), .C(G17gat), .Y(new_n75_));
  NOR2   g032(.A(new_n47_), .B(G24gat), .Y(new_n76_));
  INV    g033(.A(G1gat), .Y(new_n77_));
  INV    g034(.A(G24gat), .Y(new_n78_));
  AOI22  g035(.A0(G30gat), .A1(new_n78_), .B0(G4gat), .B1(new_n77_), .Y(new_n79_));
  OAI21  g036(.A0(new_n44_), .A1(G11gat), .B0(new_n79_), .Y(new_n80_));
  NOR2   g037(.A(new_n73_), .B(new_n80_), .Y(new_n81_));
  XOR2   g038(.A(new_n81_), .B(new_n76_), .Y(new_n82_));
  NOR2   g039(.A(G34gat), .B(new_n47_), .Y(new_n83_));
  NOR2   g040(.A(new_n46_), .B(G1gat), .Y(new_n84_));
  XOR2   g041(.A(new_n81_), .B(new_n84_), .Y(new_n85_));
  NOR2   g042(.A(G8gat), .B(new_n46_), .Y(new_n86_));
  AOI22  g043(.A0(new_n86_), .A1(new_n85_), .B0(new_n83_), .B1(new_n82_), .Y(new_n87_));
  NOR2   g044(.A(new_n51_), .B(G102gat), .Y(new_n88_));
  XOR2   g045(.A(new_n81_), .B(new_n88_), .Y(new_n89_));
  NOR2   g046(.A(G112gat), .B(new_n51_), .Y(new_n90_));
  NOR2   g047(.A(new_n50_), .B(G89gat), .Y(new_n91_));
  XOR2   g048(.A(new_n81_), .B(new_n91_), .Y(new_n92_));
  NOR2   g049(.A(G99gat), .B(new_n50_), .Y(new_n93_));
  AOI22  g050(.A0(new_n93_), .A1(new_n92_), .B0(new_n90_), .B1(new_n89_), .Y(new_n94_));
  NAND3  g051(.A(new_n60_), .B(new_n67_), .C(new_n49_), .Y(new_n95_));
  OAI21  g052(.A0(new_n73_), .A1(new_n80_), .B0(new_n54_), .Y(new_n96_));
  AOI211 g053(.A0(new_n96_), .A1(new_n95_), .B(G86gat), .C(new_n53_), .Y(new_n97_));
  NAND3  g054(.A(new_n60_), .B(new_n69_), .C(new_n49_), .Y(new_n98_));
  OAI21  g055(.A0(new_n73_), .A1(new_n80_), .B0(new_n56_), .Y(new_n99_));
  AOI211 g056(.A0(new_n99_), .A1(new_n98_), .B(G73gat), .C(new_n55_), .Y(new_n100_));
  NAND2  g057(.A(G56gat), .B(new_n71_), .Y(new_n101_));
  NAND3  g058(.A(new_n60_), .B(new_n101_), .C(new_n49_), .Y(new_n102_));
  NOR2   g059(.A(new_n58_), .B(G50gat), .Y(new_n103_));
  OAI21  g060(.A0(new_n73_), .A1(new_n80_), .B0(new_n103_), .Y(new_n104_));
  AOI211 g061(.A0(new_n104_), .A1(new_n102_), .B(G60gat), .C(new_n58_), .Y(new_n105_));
  NAND2  g062(.A(G43gat), .B(new_n70_), .Y(new_n106_));
  NAND3  g063(.A(new_n60_), .B(new_n106_), .C(new_n49_), .Y(new_n107_));
  NOR2   g064(.A(new_n57_), .B(G37gat), .Y(new_n108_));
  OAI21  g065(.A0(new_n73_), .A1(new_n80_), .B0(new_n108_), .Y(new_n109_));
  AOI211 g066(.A0(new_n109_), .A1(new_n107_), .B(G47gat), .C(new_n57_), .Y(new_n110_));
  NOR4   g067(.A(new_n110_), .B(new_n105_), .C(new_n100_), .D(new_n97_), .Y(new_n111_));
  NAND4  g068(.A(new_n111_), .B(new_n94_), .C(new_n87_), .D(new_n75_), .Y(G329gat));
  AOI21  g069(.A0(new_n60_), .A1(new_n79_), .B0(new_n45_), .Y(new_n113_));
  NAND4  g070(.A(new_n111_), .B(new_n94_), .C(new_n87_), .D(new_n75_), .Y(new_n114_));
  XOR2   g071(.A(G223gat), .B(new_n88_), .Y(new_n115_));
  INV    g072(.A(new_n90_), .Y(new_n116_));
  XOR2   g073(.A(G223gat), .B(new_n91_), .Y(new_n117_));
  INV    g074(.A(new_n93_), .Y(new_n118_));
  OAI22  g075(.A0(new_n118_), .A1(new_n117_), .B0(new_n116_), .B1(new_n115_), .Y(new_n119_));
  INV    g076(.A(G86gat), .Y(new_n120_));
  NOR3   g077(.A(new_n73_), .B(new_n54_), .C(new_n80_), .Y(new_n121_));
  AOI21  g078(.A0(new_n60_), .A1(new_n49_), .B0(new_n67_), .Y(new_n122_));
  OAI211 g079(.A0(new_n122_), .A1(new_n121_), .B0(new_n120_), .B1(G82gat), .Y(new_n123_));
  INV    g080(.A(G73gat), .Y(new_n124_));
  NOR3   g081(.A(new_n73_), .B(new_n56_), .C(new_n80_), .Y(new_n125_));
  AOI21  g082(.A0(new_n60_), .A1(new_n49_), .B0(new_n69_), .Y(new_n126_));
  OAI211 g083(.A0(new_n126_), .A1(new_n125_), .B0(new_n124_), .B1(G69gat), .Y(new_n127_));
  INV    g084(.A(G60gat), .Y(new_n128_));
  NOR3   g085(.A(new_n73_), .B(new_n103_), .C(new_n80_), .Y(new_n129_));
  AOI21  g086(.A0(new_n60_), .A1(new_n49_), .B0(new_n101_), .Y(new_n130_));
  OAI211 g087(.A0(new_n130_), .A1(new_n129_), .B0(new_n128_), .B1(G56gat), .Y(new_n131_));
  INV    g088(.A(G47gat), .Y(new_n132_));
  NOR3   g089(.A(new_n73_), .B(new_n108_), .C(new_n80_), .Y(new_n133_));
  AOI21  g090(.A0(new_n60_), .A1(new_n49_), .B0(new_n106_), .Y(new_n134_));
  OAI211 g091(.A0(new_n134_), .A1(new_n133_), .B0(new_n132_), .B1(G43gat), .Y(new_n135_));
  NAND4  g092(.A(new_n135_), .B(new_n131_), .C(new_n127_), .D(new_n123_), .Y(new_n136_));
  NOR3   g093(.A(new_n113_), .B(G21gat), .C(new_n44_), .Y(new_n138_));
  INV    g094(.A(new_n138_), .Y(new_n139_));
  INV    g095(.A(G27gat), .Y(new_n140_));
  NAND2  g096(.A(new_n140_), .B(G17gat), .Y(new_n141_));
  AOI211 g097(.A0(new_n139_), .A1(new_n114_), .B(new_n141_), .C(new_n113_), .Y(new_n142_));
  NAND2  g098(.A(new_n83_), .B(new_n82_), .Y(new_n143_));
  XOR2   g099(.A(G329gat), .B(new_n143_), .Y(new_n144_));
  XOR2   g100(.A(G223gat), .B(new_n76_), .Y(new_n145_));
  NOR3   g101(.A(new_n145_), .B(G40gat), .C(new_n47_), .Y(new_n146_));
  NAND2  g102(.A(new_n86_), .B(new_n85_), .Y(new_n147_));
  XOR2   g103(.A(G329gat), .B(new_n147_), .Y(new_n148_));
  XOR2   g104(.A(G223gat), .B(new_n84_), .Y(new_n149_));
  NOR3   g105(.A(new_n149_), .B(G14gat), .C(new_n46_), .Y(new_n150_));
  AOI221 g106(.A0(new_n150_), .A1(new_n148_), .C0(new_n142_), .B0(new_n146_), .B1(new_n144_), .Y(new_n151_));
  NAND2  g107(.A(new_n90_), .B(new_n89_), .Y(new_n152_));
  XOR2   g108(.A(G329gat), .B(new_n152_), .Y(new_n153_));
  NOR3   g109(.A(new_n115_), .B(G115gat), .C(new_n51_), .Y(new_n154_));
  NAND2  g110(.A(new_n93_), .B(new_n92_), .Y(new_n155_));
  XOR2   g111(.A(G329gat), .B(new_n155_), .Y(new_n156_));
  NOR3   g112(.A(new_n117_), .B(G105gat), .C(new_n50_), .Y(new_n157_));
  AOI22  g113(.A0(new_n157_), .A1(new_n156_), .B0(new_n154_), .B1(new_n153_), .Y(new_n158_));
  NOR3   g114(.A(new_n113_), .B(G21gat), .C(new_n44_), .Y(new_n159_));
  INV    g115(.A(new_n83_), .Y(new_n160_));
  INV    g116(.A(new_n86_), .Y(new_n161_));
  OAI22  g117(.A0(new_n161_), .A1(new_n149_), .B0(new_n160_), .B1(new_n145_), .Y(new_n162_));
  NOR4   g118(.A(new_n136_), .B(new_n119_), .C(new_n162_), .D(new_n159_), .Y(new_n163_));
  XOR2   g119(.A(new_n163_), .B(new_n97_), .Y(new_n164_));
  AOI211 g120(.A0(new_n96_), .A1(new_n95_), .B(G92gat), .C(new_n53_), .Y(new_n165_));
  XOR2   g121(.A(new_n163_), .B(new_n100_), .Y(new_n166_));
  AOI211 g122(.A0(new_n99_), .A1(new_n98_), .B(G79gat), .C(new_n55_), .Y(new_n167_));
  AOI22  g123(.A0(new_n167_), .A1(new_n166_), .B0(new_n165_), .B1(new_n164_), .Y(new_n168_));
  XOR2   g124(.A(new_n163_), .B(new_n105_), .Y(new_n169_));
  AOI211 g125(.A0(new_n104_), .A1(new_n102_), .B(G66gat), .C(new_n58_), .Y(new_n170_));
  XOR2   g126(.A(new_n163_), .B(new_n110_), .Y(new_n171_));
  AOI211 g127(.A0(new_n109_), .A1(new_n107_), .B(G53gat), .C(new_n57_), .Y(new_n172_));
  AOI22  g128(.A0(new_n172_), .A1(new_n171_), .B0(new_n170_), .B1(new_n169_), .Y(new_n173_));
  NAND4  g129(.A(new_n173_), .B(new_n168_), .C(new_n158_), .D(new_n151_), .Y(G370gat));
  OAI21  g130(.A0(new_n81_), .A1(new_n77_), .B0(G4gat), .Y(new_n175_));
  AOI221 g131(.A0(G370gat), .A1(G14gat), .C0(new_n175_), .B0(G329gat), .B1(G8gat), .Y(new_n176_));
  AOI221 g132(.A0(G329gat), .A1(G86gat), .C0(new_n53_), .B0(G223gat), .B1(G76gat), .Y(new_n177_));
  INV    g133(.A(new_n177_), .Y(new_n178_));
  AOI21  g134(.A0(G370gat), .A1(G92gat), .B0(new_n178_), .Y(new_n179_));
  OAI221 g135(.A0(new_n163_), .A1(new_n124_), .C0(G69gat), .B0(new_n81_), .B1(new_n68_), .Y(new_n180_));
  AOI21  g136(.A0(G370gat), .A1(G79gat), .B0(new_n180_), .Y(new_n181_));
  AOI221 g137(.A0(G329gat), .A1(G60gat), .C0(new_n58_), .B0(G223gat), .B1(G50gat), .Y(new_n182_));
  INV    g138(.A(new_n182_), .Y(new_n183_));
  AOI21  g139(.A0(G370gat), .A1(G66gat), .B0(new_n183_), .Y(new_n184_));
  AOI221 g140(.A0(G329gat), .A1(G47gat), .C0(new_n57_), .B0(G223gat), .B1(G37gat), .Y(new_n185_));
  INV    g141(.A(new_n185_), .Y(new_n186_));
  AOI21  g142(.A0(G370gat), .A1(G53gat), .B0(new_n186_), .Y(new_n187_));
  NOR4   g143(.A(new_n187_), .B(new_n184_), .C(new_n181_), .D(new_n179_), .Y(new_n188_));
  AOI21  g144(.A0(G223gat), .A1(G11gat), .B0(new_n44_), .Y(new_n189_));
  OAI21  g145(.A0(new_n163_), .A1(new_n62_), .B0(new_n189_), .Y(new_n190_));
  AOI21  g146(.A0(G370gat), .A1(G27gat), .B0(new_n190_), .Y(new_n191_));
  AOI221 g147(.A0(G329gat), .A1(G34gat), .C0(new_n47_), .B0(G223gat), .B1(G24gat), .Y(new_n192_));
  INV    g148(.A(new_n192_), .Y(new_n193_));
  AOI21  g149(.A0(G370gat), .A1(G40gat), .B0(new_n193_), .Y(new_n194_));
  OAI21  g150(.A0(new_n81_), .A1(new_n64_), .B0(G108gat), .Y(new_n195_));
  AOI221 g151(.A0(G370gat), .A1(G115gat), .C0(new_n195_), .B0(G329gat), .B1(G112gat), .Y(new_n196_));
  OAI21  g152(.A0(new_n81_), .A1(new_n63_), .B0(G95gat), .Y(new_n197_));
  AOI221 g153(.A0(G370gat), .A1(G105gat), .C0(new_n197_), .B0(G329gat), .B1(G99gat), .Y(new_n198_));
  NOR4   g154(.A(new_n198_), .B(new_n196_), .C(new_n194_), .D(new_n191_), .Y(new_n199_));
  AOI21  g155(.A0(new_n199_), .A1(new_n188_), .B0(new_n176_), .Y(G421gat));
  INV    g156(.A(G66gat), .Y(new_n201_));
  NOR4   g157(.A(new_n136_), .B(new_n119_), .C(new_n162_), .D(new_n159_), .Y(new_n202_));
  NOR2   g158(.A(new_n141_), .B(new_n113_), .Y(new_n203_));
  OAI21  g159(.A0(new_n138_), .A1(new_n202_), .B0(new_n203_), .Y(new_n204_));
  XOR2   g160(.A(new_n163_), .B(new_n143_), .Y(new_n205_));
  INV    g161(.A(new_n146_), .Y(new_n206_));
  XOR2   g162(.A(new_n163_), .B(new_n147_), .Y(new_n207_));
  INV    g163(.A(new_n150_), .Y(new_n208_));
  OAI221 g164(.A0(new_n208_), .A1(new_n207_), .C0(new_n204_), .B0(new_n206_), .B1(new_n205_), .Y(new_n209_));
  XOR2   g165(.A(new_n163_), .B(new_n152_), .Y(new_n210_));
  INV    g166(.A(new_n154_), .Y(new_n211_));
  XOR2   g167(.A(new_n163_), .B(new_n155_), .Y(new_n212_));
  INV    g168(.A(new_n157_), .Y(new_n213_));
  OAI22  g169(.A0(new_n213_), .A1(new_n212_), .B0(new_n211_), .B1(new_n210_), .Y(new_n214_));
  XOR2   g170(.A(new_n163_), .B(new_n123_), .Y(new_n215_));
  INV    g171(.A(new_n165_), .Y(new_n216_));
  XOR2   g172(.A(new_n163_), .B(new_n127_), .Y(new_n217_));
  INV    g173(.A(new_n167_), .Y(new_n218_));
  OAI22  g174(.A0(new_n218_), .A1(new_n217_), .B0(new_n216_), .B1(new_n215_), .Y(new_n219_));
  XOR2   g175(.A(new_n163_), .B(new_n131_), .Y(new_n220_));
  INV    g176(.A(new_n170_), .Y(new_n221_));
  XOR2   g177(.A(new_n163_), .B(new_n135_), .Y(new_n222_));
  INV    g178(.A(new_n172_), .Y(new_n223_));
  OAI22  g179(.A0(new_n223_), .A1(new_n222_), .B0(new_n221_), .B1(new_n220_), .Y(new_n224_));
  NOR4   g180(.A(new_n224_), .B(new_n219_), .C(new_n214_), .D(new_n209_), .Y(new_n225_));
  OAI21  g181(.A0(new_n225_), .A1(new_n201_), .B0(new_n182_), .Y(new_n226_));
  INV    g182(.A(G53gat), .Y(new_n227_));
  OAI21  g183(.A0(new_n225_), .A1(new_n227_), .B0(new_n185_), .Y(new_n228_));
  OAI221 g184(.A0(new_n225_), .A1(new_n140_), .C0(new_n189_), .B0(new_n163_), .B1(new_n62_), .Y(new_n229_));
  INV    g185(.A(G40gat), .Y(new_n230_));
  OAI21  g186(.A0(new_n225_), .A1(new_n230_), .B0(new_n192_), .Y(new_n231_));
  NAND4  g187(.A(new_n231_), .B(new_n229_), .C(new_n228_), .D(new_n226_), .Y(G430gat));
  NAND3  g188(.A(new_n228_), .B(new_n226_), .C(new_n179_), .Y(new_n233_));
  NAND4  g189(.A(new_n231_), .B(new_n228_), .C(new_n226_), .D(new_n181_), .Y(new_n234_));
  NAND4  g190(.A(new_n234_), .B(new_n233_), .C(new_n231_), .D(new_n229_), .Y(G431gat));
  INV    g191(.A(G92gat), .Y(new_n236_));
  OAI21  g192(.A0(new_n225_), .A1(new_n236_), .B0(new_n177_), .Y(new_n237_));
  NAND4  g193(.A(new_n198_), .B(new_n231_), .C(new_n228_), .D(new_n237_), .Y(new_n238_));
  AOI21  g194(.A0(new_n231_), .A1(new_n187_), .B0(new_n191_), .Y(new_n239_));
  NAND3  g195(.A(new_n239_), .B(new_n238_), .C(new_n234_), .Y(G432gat));
endmodule


