// Benchmark "c432.blif" written by ABC on Wed Mar 31 18:38:51 2021

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
    new_n58_, new_n59_, new_n61_, new_n62_, new_n63_, new_n64_, new_n65_,
    new_n66_, new_n67_, new_n68_, new_n69_, new_n70_, new_n71_, new_n72_,
    new_n73_, new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_,
    new_n80_, new_n81_, new_n82_, new_n83_, new_n84_, new_n85_, new_n86_,
    new_n87_, new_n88_, new_n89_, new_n90_, new_n91_, new_n92_, new_n93_,
    new_n94_, new_n95_, new_n96_, new_n97_, new_n98_, new_n99_, new_n100_,
    new_n101_, new_n102_, new_n103_, new_n104_, new_n105_, new_n106_,
    new_n107_, new_n109_, new_n110_, new_n111_, new_n112_, new_n113_,
    new_n114_, new_n115_, new_n116_, new_n117_, new_n118_, new_n119_,
    new_n120_, new_n121_, new_n122_, new_n123_, new_n124_, new_n125_,
    new_n126_, new_n127_, new_n128_, new_n129_, new_n130_, new_n131_,
    new_n132_, new_n133_, new_n134_, new_n135_, new_n136_, new_n137_,
    new_n138_, new_n139_, new_n140_, new_n141_, new_n142_, new_n143_,
    new_n144_, new_n145_, new_n146_, new_n147_, new_n148_, new_n149_,
    new_n150_, new_n151_, new_n152_, new_n153_, new_n154_, new_n155_,
    new_n156_, new_n157_, new_n158_, new_n159_, new_n160_, new_n161_,
    new_n162_, new_n163_, new_n164_, new_n165_, new_n166_, new_n167_,
    new_n168_, new_n169_, new_n170_, new_n171_, new_n172_, new_n173_,
    new_n174_, new_n175_, new_n176_, new_n177_, new_n178_, new_n179_,
    new_n180_, new_n181_, new_n182_, new_n183_, new_n184_, new_n185_,
    new_n186_, new_n187_, new_n188_, new_n190_, new_n191_, new_n192_,
    new_n193_, new_n194_, new_n195_, new_n196_, new_n197_, new_n198_,
    new_n199_, new_n200_, new_n201_, new_n202_, new_n203_, new_n204_,
    new_n205_, new_n206_, new_n207_, new_n208_, new_n209_, new_n210_,
    new_n211_, new_n212_, new_n213_, new_n214_, new_n216_, new_n217_,
    new_n218_, new_n219_, new_n220_, new_n221_, new_n222_, new_n223_,
    new_n224_, new_n225_, new_n226_, new_n227_, new_n228_, new_n229_,
    new_n230_, new_n231_, new_n232_, new_n233_, new_n234_, new_n235_,
    new_n236_, new_n237_, new_n238_, new_n239_, new_n240_, new_n241_,
    new_n242_, new_n243_, new_n244_, new_n245_, new_n247_, new_n248_,
    new_n250_, new_n251_, new_n252_, new_n253_;
  INV    g000(.A(G17gat), .Y(new_n44_));
  INV    g001(.A(G1gat), .Y(new_n45_));
  INV    g002(.A(G24gat), .Y(new_n46_));
  AOI22  g003(.A0(G30gat), .A1(new_n46_), .B0(G4gat), .B1(new_n45_), .Y(new_n47_));
  OAI21  g004(.A0(new_n44_), .A1(G11gat), .B0(new_n47_), .Y(new_n48_));
  INV    g005(.A(G89gat), .Y(new_n49_));
  INV    g006(.A(G102gat), .Y(new_n50_));
  AOI22  g007(.A0(G108gat), .A1(new_n50_), .B0(G95gat), .B1(new_n49_), .Y(new_n51_));
  INV    g008(.A(G63gat), .Y(new_n52_));
  INV    g009(.A(G76gat), .Y(new_n53_));
  AOI22  g010(.A0(G82gat), .A1(new_n53_), .B0(G69gat), .B1(new_n52_), .Y(new_n54_));
  INV    g011(.A(G37gat), .Y(new_n55_));
  INV    g012(.A(G50gat), .Y(new_n56_));
  AOI22  g013(.A0(G56gat), .A1(new_n56_), .B0(G43gat), .B1(new_n55_), .Y(new_n57_));
  NAND3  g014(.A(new_n57_), .B(new_n54_), .C(new_n51_), .Y(new_n58_));
  NOR2   g015(.A(new_n58_), .B(new_n48_), .Y(new_n59_));
  INV    g016(.A(new_n59_), .Y(G223gat));
  NOR2   g017(.A(new_n44_), .B(G11gat), .Y(new_n61_));
  INV    g018(.A(G95gat), .Y(new_n62_));
  INV    g019(.A(G108gat), .Y(new_n63_));
  OAI22  g020(.A0(new_n63_), .A1(G102gat), .B0(new_n62_), .B1(G89gat), .Y(new_n64_));
  INV    g021(.A(G69gat), .Y(new_n65_));
  INV    g022(.A(G82gat), .Y(new_n66_));
  OAI22  g023(.A0(new_n66_), .A1(G76gat), .B0(new_n65_), .B1(G63gat), .Y(new_n67_));
  INV    g024(.A(G43gat), .Y(new_n68_));
  INV    g025(.A(G56gat), .Y(new_n69_));
  OAI22  g026(.A0(new_n69_), .A1(G50gat), .B0(new_n68_), .B1(G37gat), .Y(new_n70_));
  NOR3   g027(.A(new_n70_), .B(new_n67_), .C(new_n64_), .Y(new_n71_));
  AOI21  g028(.A0(new_n71_), .A1(new_n47_), .B0(new_n61_), .Y(new_n72_));
  NOR3   g029(.A(new_n72_), .B(G21gat), .C(new_n44_), .Y(new_n73_));
  INV    g030(.A(G30gat), .Y(new_n74_));
  INV    g031(.A(G4gat), .Y(new_n75_));
  OAI22  g032(.A0(new_n74_), .A1(G24gat), .B0(new_n75_), .B1(G1gat), .Y(new_n76_));
  NOR2   g033(.A(new_n76_), .B(new_n61_), .Y(new_n77_));
  OAI211 g034(.A0(new_n74_), .A1(G24gat), .B0(new_n71_), .B1(new_n77_), .Y(new_n78_));
  NOR2   g035(.A(new_n74_), .B(G24gat), .Y(new_n79_));
  OAI21  g036(.A0(new_n58_), .A1(new_n48_), .B0(new_n79_), .Y(new_n80_));
  AOI211 g037(.A0(new_n80_), .A1(new_n78_), .B(G34gat), .C(new_n74_), .Y(new_n81_));
  OAI211 g038(.A0(new_n75_), .A1(G1gat), .B0(new_n71_), .B1(new_n77_), .Y(new_n82_));
  NOR2   g039(.A(new_n75_), .B(G1gat), .Y(new_n83_));
  INV    g040(.A(new_n83_), .Y(new_n84_));
  AOI211 g041(.A0(new_n84_), .A1(new_n82_), .B(G8gat), .C(new_n75_), .Y(new_n85_));
  NOR3   g042(.A(new_n85_), .B(new_n81_), .C(new_n73_), .Y(new_n86_));
  INV    g043(.A(G112gat), .Y(new_n87_));
  AOI211 g044(.A0(G108gat), .A1(new_n50_), .B(new_n58_), .C(new_n48_), .Y(new_n88_));
  AOI211 g045(.A0(new_n71_), .A1(new_n77_), .B(new_n63_), .C(G102gat), .Y(new_n89_));
  OAI211 g046(.A0(new_n89_), .A1(new_n88_), .B0(new_n87_), .B1(G108gat), .Y(new_n90_));
  INV    g047(.A(G99gat), .Y(new_n91_));
  AOI211 g048(.A0(G95gat), .A1(new_n49_), .B(new_n58_), .C(new_n48_), .Y(new_n92_));
  AOI211 g049(.A0(new_n71_), .A1(new_n77_), .B(new_n62_), .C(G89gat), .Y(new_n93_));
  OAI211 g050(.A0(new_n93_), .A1(new_n92_), .B0(new_n91_), .B1(G95gat), .Y(new_n94_));
  OAI211 g051(.A0(new_n66_), .A1(G76gat), .B0(new_n71_), .B1(new_n77_), .Y(new_n95_));
  OAI211 g052(.A0(new_n58_), .A1(new_n48_), .B0(G82gat), .B1(new_n53_), .Y(new_n96_));
  AOI211 g053(.A0(new_n96_), .A1(new_n95_), .B(G86gat), .C(new_n66_), .Y(new_n97_));
  OAI211 g054(.A0(new_n65_), .A1(G63gat), .B0(new_n71_), .B1(new_n77_), .Y(new_n98_));
  OAI211 g055(.A0(new_n58_), .A1(new_n48_), .B0(G69gat), .B1(new_n52_), .Y(new_n99_));
  AOI211 g056(.A0(new_n99_), .A1(new_n98_), .B(G73gat), .C(new_n65_), .Y(new_n100_));
  OAI211 g057(.A0(new_n69_), .A1(G50gat), .B0(new_n71_), .B1(new_n77_), .Y(new_n101_));
  OAI211 g058(.A0(new_n58_), .A1(new_n48_), .B0(G56gat), .B1(new_n56_), .Y(new_n102_));
  AOI211 g059(.A0(new_n102_), .A1(new_n101_), .B(G60gat), .C(new_n69_), .Y(new_n103_));
  OAI211 g060(.A0(new_n68_), .A1(G37gat), .B0(new_n71_), .B1(new_n77_), .Y(new_n104_));
  OAI211 g061(.A0(new_n58_), .A1(new_n48_), .B0(G43gat), .B1(new_n55_), .Y(new_n105_));
  AOI211 g062(.A0(new_n105_), .A1(new_n104_), .B(G47gat), .C(new_n68_), .Y(new_n106_));
  NOR4   g063(.A(new_n106_), .B(new_n103_), .C(new_n100_), .D(new_n97_), .Y(new_n107_));
  NAND4  g064(.A(new_n107_), .B(new_n94_), .C(new_n90_), .D(new_n86_), .Y(G329gat));
  INV    g065(.A(G21gat), .Y(new_n109_));
  OAI22  g066(.A0(new_n58_), .A1(new_n76_), .B0(new_n44_), .B1(G11gat), .Y(new_n110_));
  NAND3  g067(.A(new_n110_), .B(new_n109_), .C(G17gat), .Y(new_n111_));
  INV    g068(.A(G34gat), .Y(new_n112_));
  NOR3   g069(.A(new_n58_), .B(new_n48_), .C(new_n79_), .Y(new_n113_));
  AOI211 g070(.A0(new_n71_), .A1(new_n77_), .B(new_n74_), .C(G24gat), .Y(new_n114_));
  OAI211 g071(.A0(new_n114_), .A1(new_n113_), .B0(new_n112_), .B1(G30gat), .Y(new_n115_));
  INV    g072(.A(G8gat), .Y(new_n116_));
  AOI211 g073(.A0(G4gat), .A1(new_n45_), .B(new_n58_), .C(new_n48_), .Y(new_n117_));
  OAI211 g074(.A0(new_n83_), .A1(new_n117_), .B0(new_n116_), .B1(G4gat), .Y(new_n118_));
  NAND2  g075(.A(new_n118_), .B(new_n115_), .Y(new_n119_));
  NAND3  g076(.A(new_n107_), .B(new_n94_), .C(new_n90_), .Y(new_n120_));
  OAI21  g077(.A0(new_n120_), .A1(new_n119_), .B0(new_n111_), .Y(new_n121_));
  NOR3   g078(.A(new_n72_), .B(G27gat), .C(new_n44_), .Y(new_n122_));
  NAND3  g079(.A(new_n118_), .B(new_n115_), .C(new_n111_), .Y(new_n123_));
  NOR2   g080(.A(new_n89_), .B(new_n88_), .Y(new_n124_));
  NOR3   g081(.A(new_n124_), .B(G112gat), .C(new_n63_), .Y(new_n125_));
  NOR2   g082(.A(new_n93_), .B(new_n92_), .Y(new_n126_));
  NOR3   g083(.A(new_n126_), .B(G99gat), .C(new_n62_), .Y(new_n127_));
  INV    g084(.A(G86gat), .Y(new_n128_));
  AOI211 g085(.A0(G82gat), .A1(new_n53_), .B(new_n58_), .C(new_n48_), .Y(new_n129_));
  AOI211 g086(.A0(new_n71_), .A1(new_n77_), .B(new_n66_), .C(G76gat), .Y(new_n130_));
  OAI211 g087(.A0(new_n130_), .A1(new_n129_), .B0(new_n128_), .B1(G82gat), .Y(new_n131_));
  INV    g088(.A(G73gat), .Y(new_n132_));
  AOI211 g089(.A0(G69gat), .A1(new_n52_), .B(new_n58_), .C(new_n48_), .Y(new_n133_));
  AOI211 g090(.A0(new_n71_), .A1(new_n77_), .B(new_n65_), .C(G63gat), .Y(new_n134_));
  OAI211 g091(.A0(new_n134_), .A1(new_n133_), .B0(new_n132_), .B1(G69gat), .Y(new_n135_));
  INV    g092(.A(G60gat), .Y(new_n136_));
  AOI211 g093(.A0(G56gat), .A1(new_n56_), .B(new_n58_), .C(new_n48_), .Y(new_n137_));
  AOI211 g094(.A0(new_n71_), .A1(new_n77_), .B(new_n69_), .C(G50gat), .Y(new_n138_));
  OAI211 g095(.A0(new_n138_), .A1(new_n137_), .B0(new_n136_), .B1(G56gat), .Y(new_n139_));
  INV    g096(.A(G47gat), .Y(new_n140_));
  AOI211 g097(.A0(G43gat), .A1(new_n55_), .B(new_n58_), .C(new_n48_), .Y(new_n141_));
  AOI211 g098(.A0(new_n71_), .A1(new_n77_), .B(new_n68_), .C(G37gat), .Y(new_n142_));
  OAI211 g099(.A0(new_n142_), .A1(new_n141_), .B0(new_n140_), .B1(G43gat), .Y(new_n143_));
  NAND4  g100(.A(new_n143_), .B(new_n139_), .C(new_n135_), .D(new_n131_), .Y(new_n144_));
  NOR4   g101(.A(new_n144_), .B(new_n127_), .C(new_n125_), .D(new_n123_), .Y(new_n145_));
  NAND2  g102(.A(new_n145_), .B(new_n115_), .Y(new_n146_));
  NAND2  g103(.A(G329gat), .B(new_n81_), .Y(new_n147_));
  NOR2   g104(.A(G40gat), .B(new_n74_), .Y(new_n148_));
  OAI21  g105(.A0(new_n114_), .A1(new_n113_), .B0(new_n148_), .Y(new_n149_));
  AOI21  g106(.A0(new_n147_), .A1(new_n146_), .B0(new_n149_), .Y(new_n150_));
  NAND2  g107(.A(new_n145_), .B(new_n118_), .Y(new_n151_));
  NAND2  g108(.A(G329gat), .B(new_n85_), .Y(new_n152_));
  NOR2   g109(.A(G14gat), .B(new_n75_), .Y(new_n153_));
  OAI21  g110(.A0(new_n83_), .A1(new_n117_), .B0(new_n153_), .Y(new_n154_));
  AOI21  g111(.A0(new_n152_), .A1(new_n151_), .B0(new_n154_), .Y(new_n155_));
  AOI211 g112(.A0(new_n122_), .A1(new_n121_), .B(new_n155_), .C(new_n150_), .Y(new_n156_));
  NAND4  g113(.A(new_n107_), .B(new_n94_), .C(new_n90_), .D(new_n86_), .Y(new_n157_));
  NAND2  g114(.A(G329gat), .B(new_n125_), .Y(new_n158_));
  NOR2   g115(.A(G115gat), .B(new_n63_), .Y(new_n159_));
  OAI21  g116(.A0(new_n89_), .A1(new_n88_), .B0(new_n159_), .Y(new_n160_));
  AOI21  g117(.A0(new_n158_), .A1(new_n157_), .B0(new_n160_), .Y(new_n161_));
  NAND4  g118(.A(new_n107_), .B(new_n94_), .C(new_n90_), .D(new_n86_), .Y(new_n162_));
  NAND2  g119(.A(G329gat), .B(new_n127_), .Y(new_n163_));
  NOR2   g120(.A(G105gat), .B(new_n62_), .Y(new_n164_));
  OAI21  g121(.A0(new_n93_), .A1(new_n92_), .B0(new_n164_), .Y(new_n165_));
  AOI21  g122(.A0(new_n163_), .A1(new_n162_), .B0(new_n165_), .Y(new_n166_));
  NOR2   g123(.A(new_n166_), .B(new_n161_), .Y(new_n167_));
  NAND2  g124(.A(new_n145_), .B(new_n131_), .Y(new_n168_));
  NAND2  g125(.A(G329gat), .B(new_n97_), .Y(new_n169_));
  NOR2   g126(.A(G92gat), .B(new_n66_), .Y(new_n170_));
  OAI21  g127(.A0(new_n130_), .A1(new_n129_), .B0(new_n170_), .Y(new_n171_));
  AOI21  g128(.A0(new_n169_), .A1(new_n168_), .B0(new_n171_), .Y(new_n172_));
  NAND2  g129(.A(new_n145_), .B(new_n135_), .Y(new_n173_));
  NAND2  g130(.A(G329gat), .B(new_n100_), .Y(new_n174_));
  NOR2   g131(.A(G79gat), .B(new_n65_), .Y(new_n175_));
  OAI21  g132(.A0(new_n134_), .A1(new_n133_), .B0(new_n175_), .Y(new_n176_));
  AOI21  g133(.A0(new_n174_), .A1(new_n173_), .B0(new_n176_), .Y(new_n177_));
  NAND2  g134(.A(new_n145_), .B(new_n139_), .Y(new_n178_));
  NAND2  g135(.A(G329gat), .B(new_n103_), .Y(new_n179_));
  NOR2   g136(.A(G66gat), .B(new_n69_), .Y(new_n180_));
  OAI21  g137(.A0(new_n138_), .A1(new_n137_), .B0(new_n180_), .Y(new_n181_));
  AOI21  g138(.A0(new_n179_), .A1(new_n178_), .B0(new_n181_), .Y(new_n182_));
  NAND2  g139(.A(new_n145_), .B(new_n143_), .Y(new_n183_));
  NAND2  g140(.A(G329gat), .B(new_n106_), .Y(new_n184_));
  NOR2   g141(.A(G53gat), .B(new_n68_), .Y(new_n185_));
  OAI21  g142(.A0(new_n142_), .A1(new_n141_), .B0(new_n185_), .Y(new_n186_));
  AOI21  g143(.A0(new_n184_), .A1(new_n183_), .B0(new_n186_), .Y(new_n187_));
  NOR4   g144(.A(new_n187_), .B(new_n182_), .C(new_n177_), .D(new_n172_), .Y(new_n188_));
  NAND3  g145(.A(new_n188_), .B(new_n167_), .C(new_n156_), .Y(G370gat));
  OAI221 g146(.A0(new_n145_), .A1(new_n116_), .C0(G4gat), .B0(new_n59_), .B1(new_n45_), .Y(new_n190_));
  AOI21  g147(.A0(G370gat), .A1(G14gat), .B0(new_n190_), .Y(new_n191_));
  AOI221 g148(.A0(G329gat), .A1(G86gat), .C0(new_n66_), .B0(G223gat), .B1(G76gat), .Y(new_n192_));
  INV    g149(.A(new_n192_), .Y(new_n193_));
  AOI21  g150(.A0(G370gat), .A1(G92gat), .B0(new_n193_), .Y(new_n194_));
  OAI221 g151(.A0(new_n145_), .A1(new_n132_), .C0(G69gat), .B0(new_n59_), .B1(new_n52_), .Y(new_n195_));
  AOI21  g152(.A0(G370gat), .A1(G79gat), .B0(new_n195_), .Y(new_n196_));
  AOI221 g153(.A0(G329gat), .A1(G60gat), .C0(new_n69_), .B0(G223gat), .B1(G50gat), .Y(new_n197_));
  INV    g154(.A(new_n197_), .Y(new_n198_));
  AOI21  g155(.A0(G370gat), .A1(G66gat), .B0(new_n198_), .Y(new_n199_));
  AOI221 g156(.A0(G329gat), .A1(G47gat), .C0(new_n68_), .B0(G223gat), .B1(G37gat), .Y(new_n200_));
  INV    g157(.A(new_n200_), .Y(new_n201_));
  AOI21  g158(.A0(G370gat), .A1(G53gat), .B0(new_n201_), .Y(new_n202_));
  NOR4   g159(.A(new_n202_), .B(new_n199_), .C(new_n196_), .D(new_n194_), .Y(new_n203_));
  AOI21  g160(.A0(G223gat), .A1(G11gat), .B0(new_n44_), .Y(new_n204_));
  OAI21  g161(.A0(new_n145_), .A1(new_n109_), .B0(new_n204_), .Y(new_n205_));
  AOI21  g162(.A0(G370gat), .A1(G27gat), .B0(new_n205_), .Y(new_n206_));
  AOI221 g163(.A0(G329gat), .A1(G34gat), .C0(new_n74_), .B0(G223gat), .B1(G24gat), .Y(new_n207_));
  INV    g164(.A(new_n207_), .Y(new_n208_));
  AOI21  g165(.A0(G370gat), .A1(G40gat), .B0(new_n208_), .Y(new_n209_));
  OAI221 g166(.A0(new_n145_), .A1(new_n87_), .C0(G108gat), .B0(new_n59_), .B1(new_n50_), .Y(new_n210_));
  AOI21  g167(.A0(G370gat), .A1(G115gat), .B0(new_n210_), .Y(new_n211_));
  OAI221 g168(.A0(new_n145_), .A1(new_n91_), .C0(G95gat), .B0(new_n59_), .B1(new_n49_), .Y(new_n212_));
  AOI21  g169(.A0(G370gat), .A1(G105gat), .B0(new_n212_), .Y(new_n213_));
  NOR4   g170(.A(new_n213_), .B(new_n211_), .C(new_n209_), .D(new_n206_), .Y(new_n214_));
  AOI21  g171(.A0(new_n214_), .A1(new_n203_), .B0(new_n191_), .Y(G421gat));
  INV    g172(.A(G66gat), .Y(new_n216_));
  NAND2  g173(.A(new_n122_), .B(new_n121_), .Y(new_n217_));
  NOR2   g174(.A(G329gat), .B(new_n81_), .Y(new_n218_));
  NOR2   g175(.A(new_n145_), .B(new_n115_), .Y(new_n219_));
  OAI221 g176(.A0(new_n219_), .A1(new_n218_), .C0(new_n148_), .B0(new_n114_), .B1(new_n113_), .Y(new_n220_));
  NOR2   g177(.A(G329gat), .B(new_n85_), .Y(new_n221_));
  NOR2   g178(.A(new_n145_), .B(new_n118_), .Y(new_n222_));
  OAI221 g179(.A0(new_n222_), .A1(new_n221_), .C0(new_n153_), .B0(new_n83_), .B1(new_n117_), .Y(new_n223_));
  NAND3  g180(.A(new_n223_), .B(new_n220_), .C(new_n217_), .Y(new_n224_));
  NOR2   g181(.A(G329gat), .B(new_n97_), .Y(new_n225_));
  NOR2   g182(.A(new_n145_), .B(new_n131_), .Y(new_n226_));
  OAI221 g183(.A0(new_n226_), .A1(new_n225_), .C0(new_n170_), .B0(new_n130_), .B1(new_n129_), .Y(new_n227_));
  NOR2   g184(.A(G329gat), .B(new_n100_), .Y(new_n228_));
  NOR2   g185(.A(new_n145_), .B(new_n135_), .Y(new_n229_));
  OAI221 g186(.A0(new_n229_), .A1(new_n228_), .C0(new_n175_), .B0(new_n134_), .B1(new_n133_), .Y(new_n230_));
  NOR2   g187(.A(G329gat), .B(new_n103_), .Y(new_n231_));
  NOR2   g188(.A(new_n145_), .B(new_n139_), .Y(new_n232_));
  OAI221 g189(.A0(new_n232_), .A1(new_n231_), .C0(new_n180_), .B0(new_n138_), .B1(new_n137_), .Y(new_n233_));
  NOR2   g190(.A(G329gat), .B(new_n106_), .Y(new_n234_));
  NOR2   g191(.A(new_n145_), .B(new_n143_), .Y(new_n235_));
  OAI221 g192(.A0(new_n235_), .A1(new_n234_), .C0(new_n185_), .B0(new_n142_), .B1(new_n141_), .Y(new_n236_));
  NAND4  g193(.A(new_n236_), .B(new_n233_), .C(new_n230_), .D(new_n227_), .Y(new_n237_));
  NOR4   g194(.A(new_n237_), .B(new_n166_), .C(new_n161_), .D(new_n224_), .Y(new_n238_));
  OAI21  g195(.A0(new_n238_), .A1(new_n216_), .B0(new_n197_), .Y(new_n239_));
  INV    g196(.A(G53gat), .Y(new_n240_));
  OAI21  g197(.A0(new_n238_), .A1(new_n240_), .B0(new_n200_), .Y(new_n241_));
  INV    g198(.A(G27gat), .Y(new_n242_));
  OAI221 g199(.A0(new_n238_), .A1(new_n242_), .C0(new_n204_), .B0(new_n145_), .B1(new_n109_), .Y(new_n243_));
  INV    g200(.A(G40gat), .Y(new_n244_));
  OAI21  g201(.A0(new_n238_), .A1(new_n244_), .B0(new_n207_), .Y(new_n245_));
  NAND4  g202(.A(new_n245_), .B(new_n243_), .C(new_n241_), .D(new_n239_), .Y(G430gat));
  NAND3  g203(.A(new_n241_), .B(new_n239_), .C(new_n194_), .Y(new_n247_));
  NAND4  g204(.A(new_n245_), .B(new_n241_), .C(new_n239_), .D(new_n196_), .Y(new_n248_));
  NAND4  g205(.A(new_n248_), .B(new_n247_), .C(new_n245_), .D(new_n243_), .Y(G431gat));
  INV    g206(.A(G92gat), .Y(new_n250_));
  OAI21  g207(.A0(new_n238_), .A1(new_n250_), .B0(new_n192_), .Y(new_n251_));
  NAND4  g208(.A(new_n213_), .B(new_n245_), .C(new_n241_), .D(new_n251_), .Y(new_n252_));
  AOI21  g209(.A0(new_n245_), .A1(new_n202_), .B0(new_n206_), .Y(new_n253_));
  NAND3  g210(.A(new_n253_), .B(new_n252_), .C(new_n248_), .Y(G432gat));
endmodule


