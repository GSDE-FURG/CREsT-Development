// Benchmark "c432.blif" written by ABC on Wed Mar 31 18:38:48 2021

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
    new_n58_, new_n59_, new_n60_, new_n61_, new_n62_, new_n63_, new_n64_,
    new_n65_, new_n66_, new_n67_, new_n68_, new_n70_, new_n71_, new_n72_,
    new_n73_, new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_,
    new_n80_, new_n81_, new_n82_, new_n83_, new_n84_, new_n85_, new_n86_,
    new_n87_, new_n88_, new_n89_, new_n90_, new_n91_, new_n92_, new_n93_,
    new_n94_, new_n95_, new_n96_, new_n97_, new_n98_, new_n99_, new_n100_,
    new_n101_, new_n102_, new_n103_, new_n104_, new_n105_, new_n106_,
    new_n107_, new_n108_, new_n109_, new_n110_, new_n111_, new_n112_,
    new_n113_, new_n114_, new_n115_, new_n116_, new_n117_, new_n118_,
    new_n119_, new_n120_, new_n121_, new_n122_, new_n123_, new_n124_,
    new_n125_, new_n126_, new_n127_, new_n128_, new_n129_, new_n130_,
    new_n131_, new_n132_, new_n133_, new_n134_, new_n135_, new_n136_,
    new_n137_, new_n138_, new_n139_, new_n140_, new_n141_, new_n142_,
    new_n143_, new_n144_, new_n145_, new_n146_, new_n147_, new_n148_,
    new_n149_, new_n150_, new_n151_, new_n152_, new_n153_, new_n154_,
    new_n155_, new_n157_, new_n158_, new_n159_, new_n160_, new_n161_,
    new_n162_, new_n163_, new_n164_, new_n165_, new_n166_, new_n167_,
    new_n168_, new_n169_, new_n170_, new_n171_, new_n172_, new_n173_,
    new_n174_, new_n175_, new_n176_, new_n177_, new_n178_, new_n179_,
    new_n180_, new_n181_, new_n182_, new_n183_, new_n184_, new_n185_,
    new_n186_, new_n187_, new_n188_, new_n189_, new_n190_, new_n191_,
    new_n192_, new_n193_, new_n194_, new_n195_, new_n196_, new_n197_,
    new_n198_, new_n199_, new_n200_, new_n201_, new_n202_, new_n203_,
    new_n204_, new_n205_, new_n206_, new_n207_, new_n208_, new_n209_,
    new_n210_, new_n211_, new_n212_, new_n213_, new_n214_, new_n215_,
    new_n216_, new_n217_, new_n218_, new_n219_, new_n220_, new_n221_,
    new_n222_, new_n223_, new_n224_, new_n225_, new_n226_, new_n227_,
    new_n228_, new_n229_, new_n230_, new_n231_, new_n232_, new_n233_,
    new_n234_, new_n235_, new_n236_, new_n237_, new_n238_, new_n239_,
    new_n240_, new_n241_, new_n242_, new_n243_, new_n244_, new_n245_,
    new_n246_, new_n247_, new_n248_, new_n249_, new_n250_, new_n251_,
    new_n252_, new_n253_, new_n254_, new_n255_, new_n256_, new_n257_,
    new_n258_, new_n259_, new_n260_, new_n261_, new_n262_, new_n263_,
    new_n265_, new_n266_, new_n267_, new_n268_, new_n269_, new_n270_,
    new_n271_, new_n272_, new_n273_, new_n274_, new_n275_, new_n276_,
    new_n277_, new_n278_, new_n279_, new_n280_, new_n281_, new_n282_,
    new_n283_, new_n284_, new_n285_, new_n286_, new_n287_, new_n288_,
    new_n289_, new_n290_, new_n291_, new_n292_, new_n293_, new_n294_,
    new_n295_, new_n296_, new_n297_, new_n298_, new_n299_, new_n300_,
    new_n301_, new_n302_, new_n303_, new_n304_, new_n305_, new_n306_,
    new_n307_, new_n308_, new_n309_, new_n310_, new_n311_, new_n312_,
    new_n313_, new_n314_, new_n315_, new_n316_, new_n317_, new_n318_,
    new_n319_, new_n320_, new_n321_, new_n322_, new_n323_, new_n324_,
    new_n325_, new_n326_, new_n327_, new_n328_, new_n329_, new_n330_,
    new_n331_, new_n332_, new_n333_, new_n334_, new_n335_, new_n336_,
    new_n337_, new_n338_, new_n339_, new_n340_, new_n341_, new_n342_,
    new_n343_, new_n344_, new_n345_, new_n346_, new_n347_, new_n348_,
    new_n349_, new_n350_, new_n351_, new_n352_, new_n353_, new_n354_,
    new_n355_, new_n356_, new_n357_, new_n358_, new_n359_, new_n360_,
    new_n361_, new_n362_, new_n363_, new_n364_, new_n365_, new_n366_,
    new_n367_, new_n368_, new_n369_, new_n370_, new_n371_, new_n372_,
    new_n373_, new_n374_, new_n375_, new_n376_, new_n377_, new_n378_,
    new_n379_, new_n380_, new_n382_, new_n383_, new_n384_, new_n385_,
    new_n386_, new_n387_, new_n388_, new_n389_, new_n390_, new_n392_,
    new_n393_, new_n394_, new_n395_, new_n396_, new_n397_, new_n398_,
    new_n399_, new_n400_, new_n401_, new_n402_, new_n403_, new_n404_,
    new_n405_, new_n406_, new_n408_, new_n409_, new_n410_, new_n411_,
    new_n412_, new_n413_, new_n414_, new_n415_;
  INV   g000(.A(G17gat), .Y(new_n44_));
  NOR2  g001(.A(new_n44_), .B(G11gat), .Y(new_n45_));
  INV   g002(.A(G24gat), .Y(new_n46_));
  NAND2 g003(.A(G30gat), .B(new_n46_), .Y(new_n47_));
  INV   g004(.A(G1gat), .Y(new_n48_));
  NAND2 g005(.A(G4gat), .B(new_n48_), .Y(new_n49_));
  NAND2 g006(.A(new_n49_), .B(new_n47_), .Y(new_n50_));
  NOR2  g007(.A(new_n50_), .B(new_n45_), .Y(new_n51_));
  INV   g008(.A(G102gat), .Y(new_n52_));
  NAND2 g009(.A(G108gat), .B(new_n52_), .Y(new_n53_));
  INV   g010(.A(G89gat), .Y(new_n54_));
  NAND2 g011(.A(G95gat), .B(new_n54_), .Y(new_n55_));
  NAND2 g012(.A(new_n55_), .B(new_n53_), .Y(new_n56_));
  INV   g013(.A(G82gat), .Y(new_n57_));
  NOR2  g014(.A(new_n57_), .B(G76gat), .Y(new_n58_));
  INV   g015(.A(G69gat), .Y(new_n59_));
  NOR2  g016(.A(new_n59_), .B(G63gat), .Y(new_n60_));
  NOR2  g017(.A(new_n60_), .B(new_n58_), .Y(new_n61_));
  INV   g018(.A(G56gat), .Y(new_n62_));
  NOR2  g019(.A(new_n62_), .B(G50gat), .Y(new_n63_));
  INV   g020(.A(G43gat), .Y(new_n64_));
  NOR2  g021(.A(new_n64_), .B(G37gat), .Y(new_n65_));
  NOR2  g022(.A(new_n65_), .B(new_n63_), .Y(new_n66_));
  NAND2 g023(.A(new_n66_), .B(new_n61_), .Y(new_n67_));
  NOR2  g024(.A(new_n67_), .B(new_n56_), .Y(new_n68_));
  NAND2 g025(.A(new_n68_), .B(new_n51_), .Y(G223gat));
  NOR2  g026(.A(G223gat), .B(new_n45_), .Y(new_n70_));
  INV   g027(.A(G30gat), .Y(new_n71_));
  NOR2  g028(.A(new_n71_), .B(G24gat), .Y(new_n72_));
  INV   g029(.A(G4gat), .Y(new_n73_));
  NOR2  g030(.A(new_n73_), .B(G1gat), .Y(new_n74_));
  NOR2  g031(.A(new_n74_), .B(new_n72_), .Y(new_n75_));
  NOR2  g032(.A(new_n44_), .B(G11gat), .Y(new_n76_));
  NOR2  g033(.A(new_n76_), .B(new_n70_), .Y(new_n77_));
  NOR2  g034(.A(G21gat), .B(new_n44_), .Y(new_n78_));
  INV   g035(.A(new_n78_), .Y(new_n79_));
  NOR2  g036(.A(new_n79_), .B(new_n77_), .Y(new_n80_));
  INV   g037(.A(new_n45_), .Y(new_n81_));
  NAND2 g038(.A(new_n75_), .B(new_n81_), .Y(new_n82_));
  INV   g039(.A(G108gat), .Y(new_n83_));
  NOR2  g040(.A(new_n83_), .B(G102gat), .Y(new_n84_));
  INV   g041(.A(G95gat), .Y(new_n85_));
  NOR2  g042(.A(new_n85_), .B(G89gat), .Y(new_n86_));
  NOR2  g043(.A(new_n86_), .B(new_n84_), .Y(new_n87_));
  INV   g044(.A(G76gat), .Y(new_n88_));
  NAND2 g045(.A(G82gat), .B(new_n88_), .Y(new_n89_));
  INV   g046(.A(G63gat), .Y(new_n90_));
  NAND2 g047(.A(G69gat), .B(new_n90_), .Y(new_n91_));
  NAND2 g048(.A(new_n91_), .B(new_n89_), .Y(new_n92_));
  INV   g049(.A(G50gat), .Y(new_n93_));
  NAND2 g050(.A(G56gat), .B(new_n93_), .Y(new_n94_));
  INV   g051(.A(G37gat), .Y(new_n95_));
  NAND2 g052(.A(G43gat), .B(new_n95_), .Y(new_n96_));
  NAND2 g053(.A(new_n96_), .B(new_n94_), .Y(new_n97_));
  NOR2  g054(.A(new_n97_), .B(new_n92_), .Y(new_n98_));
  NAND2 g055(.A(new_n98_), .B(new_n87_), .Y(new_n99_));
  NOR2  g056(.A(new_n99_), .B(new_n82_), .Y(new_n100_));
  NAND2 g057(.A(new_n100_), .B(new_n47_), .Y(new_n101_));
  NOR2  g058(.A(new_n71_), .B(G24gat), .Y(new_n102_));
  INV   g059(.A(new_n102_), .Y(new_n103_));
  NAND2 g060(.A(new_n103_), .B(new_n101_), .Y(new_n104_));
  NOR2  g061(.A(G34gat), .B(new_n71_), .Y(new_n105_));
  NAND2 g062(.A(new_n105_), .B(new_n104_), .Y(new_n106_));
  NAND2 g063(.A(new_n100_), .B(new_n49_), .Y(new_n107_));
  NOR2  g064(.A(new_n73_), .B(G1gat), .Y(new_n108_));
  INV   g065(.A(new_n108_), .Y(new_n109_));
  NAND2 g066(.A(new_n109_), .B(new_n107_), .Y(new_n110_));
  NOR2  g067(.A(G8gat), .B(new_n73_), .Y(new_n111_));
  NAND2 g068(.A(new_n111_), .B(new_n110_), .Y(new_n112_));
  NAND2 g069(.A(new_n112_), .B(new_n106_), .Y(new_n113_));
  NOR2  g070(.A(new_n113_), .B(new_n80_), .Y(new_n114_));
  NAND2 g071(.A(new_n100_), .B(new_n53_), .Y(new_n115_));
  NOR2  g072(.A(new_n83_), .B(G102gat), .Y(new_n116_));
  INV   g073(.A(new_n116_), .Y(new_n117_));
  NAND2 g074(.A(new_n117_), .B(new_n115_), .Y(new_n118_));
  NOR2  g075(.A(G112gat), .B(new_n83_), .Y(new_n119_));
  NAND2 g076(.A(new_n119_), .B(new_n118_), .Y(new_n120_));
  NAND2 g077(.A(new_n100_), .B(new_n55_), .Y(new_n121_));
  NOR2  g078(.A(new_n85_), .B(G89gat), .Y(new_n122_));
  INV   g079(.A(new_n122_), .Y(new_n123_));
  NAND2 g080(.A(new_n123_), .B(new_n121_), .Y(new_n124_));
  NOR2  g081(.A(G99gat), .B(new_n85_), .Y(new_n125_));
  NAND2 g082(.A(new_n125_), .B(new_n124_), .Y(new_n126_));
  NAND2 g083(.A(new_n126_), .B(new_n120_), .Y(new_n127_));
  NOR2  g084(.A(G223gat), .B(new_n58_), .Y(new_n128_));
  NOR2  g085(.A(new_n100_), .B(new_n89_), .Y(new_n129_));
  NOR2  g086(.A(new_n129_), .B(new_n128_), .Y(new_n130_));
  NOR2  g087(.A(G86gat), .B(new_n57_), .Y(new_n131_));
  INV   g088(.A(new_n131_), .Y(new_n132_));
  NOR2  g089(.A(new_n132_), .B(new_n130_), .Y(new_n133_));
  NOR2  g090(.A(G223gat), .B(new_n60_), .Y(new_n134_));
  NOR2  g091(.A(new_n100_), .B(new_n91_), .Y(new_n135_));
  NOR2  g092(.A(new_n135_), .B(new_n134_), .Y(new_n136_));
  NOR2  g093(.A(G73gat), .B(new_n59_), .Y(new_n137_));
  INV   g094(.A(new_n137_), .Y(new_n138_));
  NOR2  g095(.A(new_n138_), .B(new_n136_), .Y(new_n139_));
  NOR2  g096(.A(new_n139_), .B(new_n133_), .Y(new_n140_));
  NOR2  g097(.A(G223gat), .B(new_n63_), .Y(new_n141_));
  NOR2  g098(.A(new_n100_), .B(new_n94_), .Y(new_n142_));
  NOR2  g099(.A(new_n142_), .B(new_n141_), .Y(new_n143_));
  NOR2  g100(.A(G60gat), .B(new_n62_), .Y(new_n144_));
  INV   g101(.A(new_n144_), .Y(new_n145_));
  NOR2  g102(.A(new_n145_), .B(new_n143_), .Y(new_n146_));
  NOR2  g103(.A(G223gat), .B(new_n65_), .Y(new_n147_));
  NOR2  g104(.A(new_n100_), .B(new_n96_), .Y(new_n148_));
  NOR2  g105(.A(new_n148_), .B(new_n147_), .Y(new_n149_));
  NOR2  g106(.A(G47gat), .B(new_n64_), .Y(new_n150_));
  INV   g107(.A(new_n150_), .Y(new_n151_));
  NOR2  g108(.A(new_n151_), .B(new_n149_), .Y(new_n152_));
  NOR2  g109(.A(new_n152_), .B(new_n146_), .Y(new_n153_));
  NAND2 g110(.A(new_n153_), .B(new_n140_), .Y(new_n154_));
  NOR2  g111(.A(new_n154_), .B(new_n127_), .Y(new_n155_));
  NAND2 g112(.A(new_n155_), .B(new_n114_), .Y(G329gat));
  NOR2  g113(.A(G329gat), .B(new_n80_), .Y(new_n157_));
  NOR2  g114(.A(G223gat), .B(new_n72_), .Y(new_n158_));
  NOR2  g115(.A(new_n102_), .B(new_n158_), .Y(new_n159_));
  INV   g116(.A(new_n105_), .Y(new_n160_));
  NOR2  g117(.A(new_n160_), .B(new_n159_), .Y(new_n161_));
  NOR2  g118(.A(G223gat), .B(new_n74_), .Y(new_n162_));
  NOR2  g119(.A(new_n108_), .B(new_n162_), .Y(new_n163_));
  INV   g120(.A(new_n111_), .Y(new_n164_));
  NOR2  g121(.A(new_n164_), .B(new_n163_), .Y(new_n165_));
  NOR2  g122(.A(new_n165_), .B(new_n161_), .Y(new_n166_));
  NOR2  g123(.A(new_n79_), .B(new_n77_), .Y(new_n167_));
  NOR2  g124(.A(new_n167_), .B(new_n157_), .Y(new_n168_));
  INV   g125(.A(new_n77_), .Y(new_n169_));
  NOR2  g126(.A(G27gat), .B(new_n44_), .Y(new_n170_));
  NAND2 g127(.A(new_n170_), .B(new_n169_), .Y(new_n171_));
  NOR2  g128(.A(new_n171_), .B(new_n168_), .Y(new_n172_));
  INV   g129(.A(new_n80_), .Y(new_n173_));
  NAND2 g130(.A(new_n166_), .B(new_n173_), .Y(new_n174_));
  NOR2  g131(.A(G223gat), .B(new_n84_), .Y(new_n175_));
  NOR2  g132(.A(new_n116_), .B(new_n175_), .Y(new_n176_));
  INV   g133(.A(new_n119_), .Y(new_n177_));
  NOR2  g134(.A(new_n177_), .B(new_n176_), .Y(new_n178_));
  NOR2  g135(.A(G223gat), .B(new_n86_), .Y(new_n179_));
  NOR2  g136(.A(new_n122_), .B(new_n179_), .Y(new_n180_));
  INV   g137(.A(new_n125_), .Y(new_n181_));
  NOR2  g138(.A(new_n181_), .B(new_n180_), .Y(new_n182_));
  NOR2  g139(.A(new_n182_), .B(new_n178_), .Y(new_n183_));
  NAND2 g140(.A(new_n100_), .B(new_n89_), .Y(new_n184_));
  NAND2 g141(.A(G223gat), .B(new_n58_), .Y(new_n185_));
  NAND2 g142(.A(new_n185_), .B(new_n184_), .Y(new_n186_));
  NAND2 g143(.A(new_n131_), .B(new_n186_), .Y(new_n187_));
  NAND2 g144(.A(new_n100_), .B(new_n91_), .Y(new_n188_));
  NAND2 g145(.A(G223gat), .B(new_n60_), .Y(new_n189_));
  NAND2 g146(.A(new_n189_), .B(new_n188_), .Y(new_n190_));
  NAND2 g147(.A(new_n137_), .B(new_n190_), .Y(new_n191_));
  NAND2 g148(.A(new_n191_), .B(new_n187_), .Y(new_n192_));
  NAND2 g149(.A(new_n100_), .B(new_n94_), .Y(new_n193_));
  NAND2 g150(.A(G223gat), .B(new_n63_), .Y(new_n194_));
  NAND2 g151(.A(new_n194_), .B(new_n193_), .Y(new_n195_));
  NAND2 g152(.A(new_n144_), .B(new_n195_), .Y(new_n196_));
  NAND2 g153(.A(new_n100_), .B(new_n96_), .Y(new_n197_));
  NAND2 g154(.A(G223gat), .B(new_n65_), .Y(new_n198_));
  NAND2 g155(.A(new_n198_), .B(new_n197_), .Y(new_n199_));
  NAND2 g156(.A(new_n150_), .B(new_n199_), .Y(new_n200_));
  NAND2 g157(.A(new_n200_), .B(new_n196_), .Y(new_n201_));
  NOR2  g158(.A(new_n201_), .B(new_n192_), .Y(new_n202_));
  NAND2 g159(.A(new_n202_), .B(new_n183_), .Y(new_n203_));
  NOR2  g160(.A(new_n203_), .B(new_n174_), .Y(new_n204_));
  NAND2 g161(.A(new_n204_), .B(new_n106_), .Y(new_n205_));
  NAND2 g162(.A(G329gat), .B(new_n161_), .Y(new_n206_));
  NAND2 g163(.A(new_n206_), .B(new_n205_), .Y(new_n207_));
  NOR2  g164(.A(G40gat), .B(new_n71_), .Y(new_n208_));
  NAND2 g165(.A(new_n208_), .B(new_n104_), .Y(new_n209_));
  INV   g166(.A(new_n209_), .Y(new_n210_));
  NAND2 g167(.A(new_n210_), .B(new_n207_), .Y(new_n211_));
  NAND2 g168(.A(new_n204_), .B(new_n112_), .Y(new_n212_));
  NAND2 g169(.A(G329gat), .B(new_n165_), .Y(new_n213_));
  NAND2 g170(.A(new_n213_), .B(new_n212_), .Y(new_n214_));
  NOR2  g171(.A(G14gat), .B(new_n73_), .Y(new_n215_));
  NAND2 g172(.A(new_n215_), .B(new_n110_), .Y(new_n216_));
  INV   g173(.A(new_n216_), .Y(new_n217_));
  NAND2 g174(.A(new_n217_), .B(new_n214_), .Y(new_n218_));
  NAND2 g175(.A(new_n218_), .B(new_n211_), .Y(new_n219_));
  NOR2  g176(.A(new_n219_), .B(new_n172_), .Y(new_n220_));
  NAND2 g177(.A(new_n204_), .B(new_n120_), .Y(new_n221_));
  NAND2 g178(.A(G329gat), .B(new_n178_), .Y(new_n222_));
  NAND2 g179(.A(new_n222_), .B(new_n221_), .Y(new_n223_));
  NOR2  g180(.A(G115gat), .B(new_n83_), .Y(new_n224_));
  NAND2 g181(.A(new_n224_), .B(new_n118_), .Y(new_n225_));
  INV   g182(.A(new_n225_), .Y(new_n226_));
  NAND2 g183(.A(new_n226_), .B(new_n223_), .Y(new_n227_));
  NAND2 g184(.A(new_n204_), .B(new_n126_), .Y(new_n228_));
  NAND2 g185(.A(G329gat), .B(new_n182_), .Y(new_n229_));
  NAND2 g186(.A(new_n229_), .B(new_n228_), .Y(new_n230_));
  NOR2  g187(.A(G105gat), .B(new_n85_), .Y(new_n231_));
  NAND2 g188(.A(new_n231_), .B(new_n124_), .Y(new_n232_));
  INV   g189(.A(new_n232_), .Y(new_n233_));
  NAND2 g190(.A(new_n233_), .B(new_n230_), .Y(new_n234_));
  NAND2 g191(.A(new_n234_), .B(new_n227_), .Y(new_n235_));
  NOR2  g192(.A(G329gat), .B(new_n133_), .Y(new_n236_));
  NOR2  g193(.A(new_n204_), .B(new_n187_), .Y(new_n237_));
  NOR2  g194(.A(new_n237_), .B(new_n236_), .Y(new_n238_));
  NOR2  g195(.A(G92gat), .B(new_n57_), .Y(new_n239_));
  NAND2 g196(.A(new_n239_), .B(new_n186_), .Y(new_n240_));
  NOR2  g197(.A(new_n240_), .B(new_n238_), .Y(new_n241_));
  NOR2  g198(.A(G329gat), .B(new_n139_), .Y(new_n242_));
  NOR2  g199(.A(new_n204_), .B(new_n191_), .Y(new_n243_));
  NOR2  g200(.A(new_n243_), .B(new_n242_), .Y(new_n244_));
  NOR2  g201(.A(G79gat), .B(new_n59_), .Y(new_n245_));
  NAND2 g202(.A(new_n245_), .B(new_n190_), .Y(new_n246_));
  NOR2  g203(.A(new_n246_), .B(new_n244_), .Y(new_n247_));
  NOR2  g204(.A(new_n247_), .B(new_n241_), .Y(new_n248_));
  NOR2  g205(.A(G329gat), .B(new_n146_), .Y(new_n249_));
  NOR2  g206(.A(new_n204_), .B(new_n196_), .Y(new_n250_));
  NOR2  g207(.A(new_n250_), .B(new_n249_), .Y(new_n251_));
  NOR2  g208(.A(G66gat), .B(new_n62_), .Y(new_n252_));
  NAND2 g209(.A(new_n252_), .B(new_n195_), .Y(new_n253_));
  NOR2  g210(.A(new_n253_), .B(new_n251_), .Y(new_n254_));
  NOR2  g211(.A(G329gat), .B(new_n152_), .Y(new_n255_));
  NOR2  g212(.A(new_n204_), .B(new_n200_), .Y(new_n256_));
  NOR2  g213(.A(new_n256_), .B(new_n255_), .Y(new_n257_));
  NOR2  g214(.A(G53gat), .B(new_n64_), .Y(new_n258_));
  NAND2 g215(.A(new_n258_), .B(new_n199_), .Y(new_n259_));
  NOR2  g216(.A(new_n259_), .B(new_n257_), .Y(new_n260_));
  NOR2  g217(.A(new_n260_), .B(new_n254_), .Y(new_n261_));
  NAND2 g218(.A(new_n261_), .B(new_n248_), .Y(new_n262_));
  NOR2  g219(.A(new_n262_), .B(new_n235_), .Y(new_n263_));
  NAND2 g220(.A(new_n263_), .B(new_n220_), .Y(G370gat));
  INV   g221(.A(G14gat), .Y(new_n265_));
  INV   g222(.A(new_n172_), .Y(new_n266_));
  NOR2  g223(.A(G329gat), .B(new_n161_), .Y(new_n267_));
  NOR2  g224(.A(new_n204_), .B(new_n106_), .Y(new_n268_));
  NOR2  g225(.A(new_n268_), .B(new_n267_), .Y(new_n269_));
  NOR2  g226(.A(new_n209_), .B(new_n269_), .Y(new_n270_));
  NOR2  g227(.A(G329gat), .B(new_n165_), .Y(new_n271_));
  NOR2  g228(.A(new_n204_), .B(new_n112_), .Y(new_n272_));
  NOR2  g229(.A(new_n272_), .B(new_n271_), .Y(new_n273_));
  NOR2  g230(.A(new_n216_), .B(new_n273_), .Y(new_n274_));
  NOR2  g231(.A(new_n274_), .B(new_n270_), .Y(new_n275_));
  NAND2 g232(.A(new_n275_), .B(new_n266_), .Y(new_n276_));
  NOR2  g233(.A(G329gat), .B(new_n178_), .Y(new_n277_));
  NOR2  g234(.A(new_n204_), .B(new_n120_), .Y(new_n278_));
  NOR2  g235(.A(new_n278_), .B(new_n277_), .Y(new_n279_));
  NOR2  g236(.A(new_n225_), .B(new_n279_), .Y(new_n280_));
  NOR2  g237(.A(G329gat), .B(new_n182_), .Y(new_n281_));
  NOR2  g238(.A(new_n204_), .B(new_n126_), .Y(new_n282_));
  NOR2  g239(.A(new_n282_), .B(new_n281_), .Y(new_n283_));
  NOR2  g240(.A(new_n232_), .B(new_n283_), .Y(new_n284_));
  NOR2  g241(.A(new_n284_), .B(new_n280_), .Y(new_n285_));
  NAND2 g242(.A(new_n204_), .B(new_n187_), .Y(new_n286_));
  NAND2 g243(.A(G329gat), .B(new_n133_), .Y(new_n287_));
  NAND2 g244(.A(new_n287_), .B(new_n286_), .Y(new_n288_));
  INV   g245(.A(new_n240_), .Y(new_n289_));
  NAND2 g246(.A(new_n289_), .B(new_n288_), .Y(new_n290_));
  NAND2 g247(.A(new_n204_), .B(new_n191_), .Y(new_n291_));
  NAND2 g248(.A(G329gat), .B(new_n139_), .Y(new_n292_));
  NAND2 g249(.A(new_n292_), .B(new_n291_), .Y(new_n293_));
  INV   g250(.A(new_n246_), .Y(new_n294_));
  NAND2 g251(.A(new_n294_), .B(new_n293_), .Y(new_n295_));
  NAND2 g252(.A(new_n295_), .B(new_n290_), .Y(new_n296_));
  NAND2 g253(.A(new_n204_), .B(new_n196_), .Y(new_n297_));
  NAND2 g254(.A(G329gat), .B(new_n146_), .Y(new_n298_));
  NAND2 g255(.A(new_n298_), .B(new_n297_), .Y(new_n299_));
  INV   g256(.A(new_n253_), .Y(new_n300_));
  NAND2 g257(.A(new_n300_), .B(new_n299_), .Y(new_n301_));
  NAND2 g258(.A(new_n204_), .B(new_n200_), .Y(new_n302_));
  NAND2 g259(.A(G329gat), .B(new_n152_), .Y(new_n303_));
  NAND2 g260(.A(new_n303_), .B(new_n302_), .Y(new_n304_));
  INV   g261(.A(new_n259_), .Y(new_n305_));
  NAND2 g262(.A(new_n305_), .B(new_n304_), .Y(new_n306_));
  NAND2 g263(.A(new_n306_), .B(new_n301_), .Y(new_n307_));
  NOR2  g264(.A(new_n307_), .B(new_n296_), .Y(new_n308_));
  NAND2 g265(.A(new_n308_), .B(new_n285_), .Y(new_n309_));
  NOR2  g266(.A(new_n309_), .B(new_n276_), .Y(new_n310_));
  NOR2  g267(.A(new_n310_), .B(new_n265_), .Y(new_n311_));
  NAND2 g268(.A(G329gat), .B(G8gat), .Y(new_n312_));
  NOR2  g269(.A(new_n100_), .B(new_n48_), .Y(new_n313_));
  NOR2  g270(.A(new_n313_), .B(new_n73_), .Y(new_n314_));
  NAND2 g271(.A(new_n314_), .B(new_n312_), .Y(new_n315_));
  NOR2  g272(.A(new_n315_), .B(new_n311_), .Y(new_n316_));
  INV   g273(.A(G92gat), .Y(new_n317_));
  NOR2  g274(.A(new_n310_), .B(new_n317_), .Y(new_n318_));
  NAND2 g275(.A(G329gat), .B(G86gat), .Y(new_n319_));
  NOR2  g276(.A(new_n100_), .B(new_n88_), .Y(new_n320_));
  NOR2  g277(.A(new_n320_), .B(new_n57_), .Y(new_n321_));
  NAND2 g278(.A(new_n321_), .B(new_n319_), .Y(new_n322_));
  NOR2  g279(.A(new_n322_), .B(new_n318_), .Y(new_n323_));
  INV   g280(.A(G79gat), .Y(new_n324_));
  NOR2  g281(.A(new_n310_), .B(new_n324_), .Y(new_n325_));
  NAND2 g282(.A(G329gat), .B(G73gat), .Y(new_n326_));
  NOR2  g283(.A(new_n100_), .B(new_n90_), .Y(new_n327_));
  NOR2  g284(.A(new_n327_), .B(new_n59_), .Y(new_n328_));
  NAND2 g285(.A(new_n328_), .B(new_n326_), .Y(new_n329_));
  NOR2  g286(.A(new_n329_), .B(new_n325_), .Y(new_n330_));
  NOR2  g287(.A(new_n330_), .B(new_n323_), .Y(new_n331_));
  INV   g288(.A(G66gat), .Y(new_n332_));
  NOR2  g289(.A(new_n310_), .B(new_n332_), .Y(new_n333_));
  NAND2 g290(.A(G329gat), .B(G60gat), .Y(new_n334_));
  NOR2  g291(.A(new_n100_), .B(new_n93_), .Y(new_n335_));
  NOR2  g292(.A(new_n335_), .B(new_n62_), .Y(new_n336_));
  NAND2 g293(.A(new_n336_), .B(new_n334_), .Y(new_n337_));
  NOR2  g294(.A(new_n337_), .B(new_n333_), .Y(new_n338_));
  INV   g295(.A(G53gat), .Y(new_n339_));
  NOR2  g296(.A(new_n310_), .B(new_n339_), .Y(new_n340_));
  NAND2 g297(.A(G329gat), .B(G47gat), .Y(new_n341_));
  NOR2  g298(.A(new_n100_), .B(new_n95_), .Y(new_n342_));
  NOR2  g299(.A(new_n342_), .B(new_n64_), .Y(new_n343_));
  NAND2 g300(.A(new_n343_), .B(new_n341_), .Y(new_n344_));
  NOR2  g301(.A(new_n344_), .B(new_n340_), .Y(new_n345_));
  NOR2  g302(.A(new_n345_), .B(new_n338_), .Y(new_n346_));
  NAND2 g303(.A(new_n346_), .B(new_n331_), .Y(new_n347_));
  INV   g304(.A(G27gat), .Y(new_n348_));
  NOR2  g305(.A(new_n310_), .B(new_n348_), .Y(new_n349_));
  NAND2 g306(.A(G329gat), .B(G21gat), .Y(new_n350_));
  INV   g307(.A(G11gat), .Y(new_n351_));
  NOR2  g308(.A(new_n100_), .B(new_n351_), .Y(new_n352_));
  NOR2  g309(.A(new_n352_), .B(new_n44_), .Y(new_n353_));
  NAND2 g310(.A(new_n353_), .B(new_n350_), .Y(new_n354_));
  NOR2  g311(.A(new_n354_), .B(new_n349_), .Y(new_n355_));
  INV   g312(.A(G40gat), .Y(new_n356_));
  NOR2  g313(.A(new_n310_), .B(new_n356_), .Y(new_n357_));
  NAND2 g314(.A(G329gat), .B(G34gat), .Y(new_n358_));
  NOR2  g315(.A(new_n100_), .B(new_n46_), .Y(new_n359_));
  NOR2  g316(.A(new_n359_), .B(new_n71_), .Y(new_n360_));
  NAND2 g317(.A(new_n360_), .B(new_n358_), .Y(new_n361_));
  NOR2  g318(.A(new_n361_), .B(new_n357_), .Y(new_n362_));
  NOR2  g319(.A(new_n362_), .B(new_n355_), .Y(new_n363_));
  INV   g320(.A(G115gat), .Y(new_n364_));
  NOR2  g321(.A(new_n310_), .B(new_n364_), .Y(new_n365_));
  NAND2 g322(.A(G329gat), .B(G112gat), .Y(new_n366_));
  NOR2  g323(.A(new_n100_), .B(new_n52_), .Y(new_n367_));
  NOR2  g324(.A(new_n367_), .B(new_n83_), .Y(new_n368_));
  NAND2 g325(.A(new_n368_), .B(new_n366_), .Y(new_n369_));
  NOR2  g326(.A(new_n369_), .B(new_n365_), .Y(new_n370_));
  INV   g327(.A(G105gat), .Y(new_n371_));
  NOR2  g328(.A(new_n310_), .B(new_n371_), .Y(new_n372_));
  NAND2 g329(.A(G329gat), .B(G99gat), .Y(new_n373_));
  NOR2  g330(.A(new_n100_), .B(new_n54_), .Y(new_n374_));
  NOR2  g331(.A(new_n374_), .B(new_n85_), .Y(new_n375_));
  NAND2 g332(.A(new_n375_), .B(new_n373_), .Y(new_n376_));
  NOR2  g333(.A(new_n376_), .B(new_n372_), .Y(new_n377_));
  NOR2  g334(.A(new_n377_), .B(new_n370_), .Y(new_n378_));
  NAND2 g335(.A(new_n378_), .B(new_n363_), .Y(new_n379_));
  NOR2  g336(.A(new_n379_), .B(new_n347_), .Y(new_n380_));
  NOR2  g337(.A(new_n380_), .B(new_n316_), .Y(G421gat));
  NAND2 g338(.A(G370gat), .B(G40gat), .Y(new_n382_));
  INV   g339(.A(new_n361_), .Y(new_n383_));
  NAND2 g340(.A(new_n383_), .B(new_n382_), .Y(new_n384_));
  NAND2 g341(.A(new_n384_), .B(new_n345_), .Y(new_n385_));
  NAND2 g342(.A(G370gat), .B(G27gat), .Y(new_n386_));
  INV   g343(.A(new_n354_), .Y(new_n387_));
  NAND2 g344(.A(new_n387_), .B(new_n386_), .Y(new_n388_));
  NAND2 g345(.A(new_n384_), .B(new_n388_), .Y(new_n389_));
  NOR2  g346(.A(new_n389_), .B(new_n338_), .Y(new_n390_));
  NAND2 g347(.A(new_n390_), .B(new_n385_), .Y(G430gat));
  NAND2 g348(.A(G370gat), .B(G53gat), .Y(new_n392_));
  INV   g349(.A(new_n344_), .Y(new_n393_));
  NAND2 g350(.A(new_n393_), .B(new_n392_), .Y(new_n394_));
  NAND2 g351(.A(G370gat), .B(G92gat), .Y(new_n395_));
  INV   g352(.A(new_n322_), .Y(new_n396_));
  NAND2 g353(.A(new_n396_), .B(new_n395_), .Y(new_n397_));
  NOR2  g354(.A(new_n338_), .B(new_n397_), .Y(new_n398_));
  NAND2 g355(.A(new_n398_), .B(new_n394_), .Y(new_n399_));
  NAND2 g356(.A(new_n384_), .B(new_n394_), .Y(new_n400_));
  NAND2 g357(.A(G370gat), .B(G66gat), .Y(new_n401_));
  INV   g358(.A(new_n337_), .Y(new_n402_));
  NAND2 g359(.A(new_n402_), .B(new_n401_), .Y(new_n403_));
  NAND2 g360(.A(new_n403_), .B(new_n330_), .Y(new_n404_));
  NOR2  g361(.A(new_n404_), .B(new_n400_), .Y(new_n405_));
  NOR2  g362(.A(new_n405_), .B(new_n389_), .Y(new_n406_));
  NAND2 g363(.A(new_n406_), .B(new_n399_), .Y(G431gat));
  NOR2  g364(.A(new_n362_), .B(new_n345_), .Y(new_n408_));
  NAND2 g365(.A(G370gat), .B(G105gat), .Y(new_n409_));
  INV   g366(.A(new_n376_), .Y(new_n410_));
  NAND2 g367(.A(new_n410_), .B(new_n409_), .Y(new_n411_));
  NOR2  g368(.A(new_n411_), .B(new_n323_), .Y(new_n412_));
  NAND2 g369(.A(new_n412_), .B(new_n408_), .Y(new_n413_));
  NAND2 g370(.A(new_n385_), .B(new_n388_), .Y(new_n414_));
  NOR2  g371(.A(new_n414_), .B(new_n405_), .Y(new_n415_));
  NAND2 g372(.A(new_n415_), .B(new_n413_), .Y(G432gat));
endmodule


