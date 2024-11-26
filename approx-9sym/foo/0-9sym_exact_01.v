// Benchmark "top" written by ABC on Wed Jun 21 04:03:54 2023

module top ( 
    i_7_, i_8_, i_5_, i_6_, i_3_, i_4_, i_1_, i_2_, i_0_,
    o_0_  );
  input  i_7_, i_8_, i_5_, i_6_, i_3_, i_4_, i_1_, i_2_, i_0_;
  output o_0_;
  wire new_n11_, new_n12_, new_n13_, new_n14_, new_n15_, new_n16_, new_n17_,
    new_n18_, new_n19_, new_n20_, new_n21_, new_n22_, new_n23_, new_n24_,
    new_n25_, new_n26_, new_n27_, new_n28_, new_n29_, new_n30_, new_n31_,
    new_n32_, new_n33_, new_n34_, new_n35_, new_n36_, new_n37_, new_n38_,
    new_n39_, new_n40_, new_n41_, new_n42_, new_n43_, new_n44_, new_n45_,
    new_n46_, new_n47_, new_n48_, new_n49_, new_n50_, new_n51_, new_n52_,
    new_n53_, new_n54_, new_n55_, new_n56_, new_n57_, new_n58_, new_n59_,
    new_n60_, new_n61_, new_n62_, new_n63_, new_n64_, new_n65_, new_n66_,
    new_n67_, new_n68_, new_n69_, new_n70_, new_n71_, new_n72_, new_n73_,
    new_n74_, new_n75_, new_n76_, new_n77_, new_n78_, new_n79_, new_n80_,
    new_n81_, new_n82_, new_n83_, new_n84_, new_n85_, new_n86_, new_n87_,
    new_n88_, new_n89_, new_n90_, new_n91_, new_n92_, new_n93_, new_n94_,
    new_n95_, new_n96_, new_n97_, new_n98_, new_n99_, new_n100_, new_n101_,
    new_n102_, new_n103_, new_n104_, new_n105_, new_n106_, new_n107_,
    new_n108_, new_n109_, new_n110_, new_n111_, new_n112_, new_n113_,
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
    new_n186_, new_n187_, new_n188_, new_n189_, new_n190_;
  NOi21      g000(.An(i_8_), .B(i_2_), .Y(new_n11_));
  NAi21      g001(.An(i_3_), .B(i_1_), .Y(new_n12_));
  NAi21      g002(.An(i_5_), .B(i_0_), .Y(new_n13_));
  NO2        g003(.A(new_n13_), .B(new_n12_), .Y(new_n14_));
  INV        g004(.A(i_2_), .Y(new_n15_));
  NO2        g005(.A(new_n15_), .B(i_8_), .Y(new_n16_));
  NAi21      g006(.An(i_5_), .B(i_1_), .Y(new_n17_));
  NAi21      g007(.An(i_7_), .B(i_3_), .Y(new_n18_));
  NO2        g008(.A(new_n18_), .B(new_n17_), .Y(new_n19_));
  AO220      g009(.A0(new_n19_), .A1(new_n16_), .B0(new_n14_), .B1(new_n11_), .Y(new_n20_));
  INV        g010(.A(i_3_), .Y(new_n21_));
  NA2        g011(.A(i_8_), .B(i_7_), .Y(new_n22_));
  NO2        g012(.A(new_n22_), .B(i_5_), .Y(new_n23_));
  NOi41      g013(.An(new_n23_), .B(i_0_), .C(i_4_), .D(new_n21_), .Y(new_n24_));
  INV        g014(.A(i_8_), .Y(new_n25_));
  NAi31      g015(.An(i_4_), .B(i_5_), .C(i_7_), .Y(new_n26_));
  NO4        g016(.A(new_n26_), .B(i_1_), .C(i_3_), .D(new_n25_), .Y(new_n27_));
  NOi21      g017(.An(i_7_), .B(i_4_), .Y(new_n28_));
  NOi31      g018(.An(i_2_), .B(i_1_), .C(i_3_), .Y(new_n29_));
  AOI210     g019(.A0(new_n28_), .A1(new_n15_), .B0(new_n29_), .Y(new_n30_));
  NOi21      g020(.An(i_0_), .B(i_6_), .Y(new_n31_));
  NA2        g021(.A(new_n31_), .B(i_5_), .Y(new_n32_));
  NAi32      g022(.An(i_5_), .Bn(i_8_), .C(i_6_), .Y(new_n33_));
  NAi21      g023(.An(i_7_), .B(i_8_), .Y(new_n34_));
  OA210      g024(.A0(new_n34_), .A1(i_6_), .B0(new_n33_), .Y(new_n35_));
  NOi21      g025(.An(i_3_), .B(i_1_), .Y(new_n36_));
  NA2        g026(.A(new_n36_), .B(i_4_), .Y(new_n37_));
  OAI220     g027(.A0(new_n37_), .A1(new_n35_), .B0(new_n32_), .B1(new_n30_), .Y(new_n38_));
  NO4        g028(.A(new_n38_), .B(new_n27_), .C(new_n24_), .D(new_n20_), .Y(new_n39_));
  INV        g029(.A(i_0_), .Y(new_n40_));
  NO2        g030(.A(new_n40_), .B(i_4_), .Y(new_n41_));
  NAi21      g031(.An(i_8_), .B(i_2_), .Y(new_n42_));
  NO2        g032(.A(new_n18_), .B(new_n42_), .Y(new_n43_));
  NOi21      g033(.An(i_6_), .B(i_1_), .Y(new_n44_));
  NOi21      g034(.An(new_n11_), .B(new_n18_), .Y(new_n45_));
  AOI220     g035(.A0(new_n45_), .A1(new_n44_), .B0(new_n43_), .B1(new_n41_), .Y(new_n46_));
  NAi21      g036(.An(i_0_), .B(i_5_), .Y(new_n47_));
  INV        g037(.A(i_1_), .Y(new_n48_));
  NAi21      g038(.An(i_8_), .B(i_6_), .Y(new_n49_));
  OR3        g039(.A(new_n49_), .B(i_2_), .C(new_n48_), .Y(new_n50_));
  NAi31      g040(.An(i_0_), .B(i_2_), .C(i_3_), .Y(new_n51_));
  INV        g041(.A(i_6_), .Y(new_n52_));
  NOi21      g042(.An(i_4_), .B(i_7_), .Y(new_n53_));
  NA2        g043(.A(new_n53_), .B(new_n52_), .Y(new_n54_));
  OA220      g044(.A0(new_n54_), .A1(new_n51_), .B0(new_n50_), .B1(new_n47_), .Y(new_n55_));
  NAi21      g045(.An(i_1_), .B(i_3_), .Y(new_n56_));
  NAi21      g046(.An(i_7_), .B(i_6_), .Y(new_n57_));
  NO3        g047(.A(new_n57_), .B(new_n47_), .C(new_n56_), .Y(new_n58_));
  NAi21      g048(.An(i_8_), .B(i_7_), .Y(new_n59_));
  NO3        g049(.A(new_n59_), .B(new_n15_), .C(i_1_), .Y(new_n60_));
  INV        g050(.A(i_4_), .Y(new_n61_));
  NO2        g051(.A(i_0_), .B(new_n61_), .Y(new_n62_));
  AOI210     g052(.A0(new_n62_), .A1(new_n60_), .B0(new_n58_), .Y(new_n63_));
  NAi21      g053(.An(i_2_), .B(i_3_), .Y(new_n64_));
  NO2        g054(.A(new_n64_), .B(new_n48_), .Y(new_n65_));
  NO2        g055(.A(new_n57_), .B(i_5_), .Y(new_n66_));
  NAi21      g056(.An(i_6_), .B(i_0_), .Y(new_n67_));
  NAi21      g057(.An(i_3_), .B(i_6_), .Y(new_n68_));
  NA2        g058(.A(new_n68_), .B(new_n67_), .Y(new_n69_));
  NAi21      g059(.An(i_7_), .B(i_4_), .Y(new_n70_));
  NO2        g060(.A(new_n70_), .B(new_n42_), .Y(new_n71_));
  AOI220     g061(.A0(new_n71_), .A1(new_n69_), .B0(new_n66_), .B1(new_n65_), .Y(new_n72_));
  AN4        g062(.A(new_n72_), .B(new_n63_), .C(new_n55_), .D(new_n46_), .Y(new_n73_));
  NOi21      g063(.An(i_2_), .B(i_4_), .Y(new_n74_));
  NOi31      g064(.An(i_7_), .B(i_1_), .C(i_6_), .Y(new_n75_));
  NOi31      g065(.An(i_6_), .B(i_0_), .C(i_8_), .Y(new_n76_));
  OAI210     g066(.A0(new_n76_), .A1(new_n75_), .B0(new_n74_), .Y(new_n77_));
  NAi32      g067(.An(i_4_), .Bn(i_3_), .C(i_0_), .Y(new_n78_));
  OR2        g068(.A(new_n78_), .B(new_n57_), .Y(new_n79_));
  NOi21      g069(.An(i_7_), .B(i_8_), .Y(new_n80_));
  NOi31      g070(.An(i_1_), .B(i_0_), .C(i_3_), .Y(new_n81_));
  OAI210     g071(.A0(new_n80_), .A1(new_n53_), .B0(new_n81_), .Y(new_n82_));
  NA3        g072(.A(new_n82_), .B(new_n79_), .C(new_n77_), .Y(new_n83_));
  NOi21      g073(.An(i_8_), .B(i_6_), .Y(new_n84_));
  AOI220     g074(.A0(new_n84_), .A1(i_5_), .B0(new_n44_), .B1(i_7_), .Y(new_n85_));
  NA2        g075(.A(new_n80_), .B(new_n52_), .Y(new_n86_));
  OAI220     g076(.A0(new_n86_), .A1(new_n21_), .B0(new_n85_), .B1(i_2_), .Y(new_n87_));
  AOI220     g077(.A0(new_n87_), .A1(new_n62_), .B0(new_n83_), .B1(i_5_), .Y(new_n88_));
  AN3        g078(.A(new_n88_), .B(new_n73_), .C(new_n39_), .Y(new_n89_));
  NAi31      g079(.An(i_7_), .B(i_6_), .C(i_8_), .Y(new_n90_));
  NOi21      g080(.An(new_n29_), .B(new_n90_), .Y(new_n91_));
  INV        g081(.A(i_7_), .Y(new_n92_));
  NOi41      g082(.An(new_n81_), .B(new_n61_), .C(i_5_), .D(new_n92_), .Y(new_n93_));
  NO3        g083(.A(new_n59_), .B(new_n51_), .C(i_5_), .Y(new_n94_));
  NO4        g084(.A(new_n67_), .B(new_n18_), .C(new_n61_), .D(i_5_), .Y(new_n95_));
  NO4        g085(.A(new_n95_), .B(new_n94_), .C(new_n93_), .D(new_n91_), .Y(new_n96_));
  NO3        g086(.A(new_n47_), .B(new_n18_), .C(new_n42_), .Y(new_n97_));
  NO4        g087(.A(new_n90_), .B(i_2_), .C(new_n61_), .D(i_3_), .Y(new_n98_));
  NAi21      g088(.An(i_6_), .B(i_8_), .Y(new_n99_));
  NA2        g089(.A(i_2_), .B(i_1_), .Y(new_n100_));
  NO4        g090(.A(new_n100_), .B(new_n99_), .C(i_3_), .D(i_5_), .Y(new_n101_));
  NO4        g091(.A(new_n99_), .B(new_n17_), .C(i_0_), .D(new_n61_), .Y(new_n102_));
  NO4        g092(.A(new_n102_), .B(new_n101_), .C(new_n98_), .D(new_n97_), .Y(new_n103_));
  NO4        g093(.A(new_n13_), .B(new_n12_), .C(new_n61_), .D(i_8_), .Y(new_n104_));
  NO3        g094(.A(new_n56_), .B(new_n26_), .C(i_0_), .Y(new_n105_));
  NO3        g095(.A(new_n26_), .B(new_n12_), .C(i_2_), .Y(new_n106_));
  INV        g096(.A(i_5_), .Y(new_n107_));
  NAi21      g097(.An(i_3_), .B(i_4_), .Y(new_n108_));
  NO4        g098(.A(new_n108_), .B(new_n49_), .C(i_1_), .D(new_n107_), .Y(new_n109_));
  NO4        g099(.A(new_n109_), .B(new_n106_), .C(new_n105_), .D(new_n104_), .Y(new_n110_));
  NAi31      g100(.An(i_4_), .B(i_0_), .C(i_2_), .Y(new_n111_));
  NO3        g101(.A(new_n111_), .B(new_n99_), .C(i_5_), .Y(new_n112_));
  NO4        g102(.A(new_n57_), .B(new_n56_), .C(new_n40_), .D(i_5_), .Y(new_n113_));
  NAi31      g103(.An(i_6_), .B(i_5_), .C(i_8_), .Y(new_n114_));
  OAI220     g104(.A0(new_n111_), .A1(new_n33_), .B0(new_n114_), .B1(new_n78_), .Y(new_n115_));
  NO3        g105(.A(new_n115_), .B(new_n113_), .C(new_n112_), .Y(new_n116_));
  NA4        g106(.A(new_n116_), .B(new_n110_), .C(new_n103_), .D(new_n96_), .Y(new_n117_));
  OAI210     g107(.A0(new_n48_), .A1(i_4_), .B0(new_n108_), .Y(new_n118_));
  NO3        g108(.A(new_n42_), .B(i_6_), .C(new_n107_), .Y(new_n119_));
  AOI220     g109(.A0(new_n119_), .A1(new_n118_), .B0(new_n60_), .B1(new_n31_), .Y(new_n120_));
  OAI210     g110(.A0(new_n62_), .A1(new_n41_), .B0(new_n19_), .Y(new_n121_));
  NO3        g111(.A(new_n40_), .B(i_2_), .C(new_n61_), .Y(new_n122_));
  NO2        g112(.A(new_n68_), .B(i_8_), .Y(new_n123_));
  NA2        g113(.A(new_n123_), .B(new_n122_), .Y(new_n124_));
  NAi32      g114(.An(i_0_), .Bn(i_2_), .C(i_1_), .Y(new_n125_));
  NO3        g115(.A(new_n125_), .B(new_n68_), .C(new_n25_), .Y(new_n126_));
  NO4        g116(.A(new_n22_), .B(new_n17_), .C(i_2_), .D(i_6_), .Y(new_n127_));
  NAi31      g117(.An(i_0_), .B(i_2_), .C(i_1_), .Y(new_n128_));
  NO4        g118(.A(new_n128_), .B(i_4_), .C(i_3_), .D(new_n52_), .Y(new_n129_));
  NO4        g119(.A(new_n64_), .B(new_n70_), .C(new_n107_), .D(i_8_), .Y(new_n130_));
  NO4        g120(.A(new_n130_), .B(new_n129_), .C(new_n127_), .D(new_n126_), .Y(new_n131_));
  NA4        g121(.A(new_n131_), .B(new_n124_), .C(new_n121_), .D(new_n120_), .Y(new_n132_));
  NO2        g122(.A(new_n25_), .B(i_7_), .Y(new_n133_));
  NA3        g123(.A(new_n122_), .B(new_n133_), .C(new_n52_), .Y(new_n134_));
  NO2        g124(.A(new_n48_), .B(i_5_), .Y(new_n135_));
  NA4        g125(.A(new_n80_), .B(new_n135_), .C(new_n15_), .D(i_6_), .Y(new_n136_));
  NO2        g126(.A(i_0_), .B(new_n107_), .Y(new_n137_));
  NO2        g127(.A(new_n61_), .B(i_3_), .Y(new_n138_));
  NA4        g128(.A(new_n138_), .B(new_n137_), .C(new_n48_), .D(i_7_), .Y(new_n139_));
  NA4        g129(.A(new_n137_), .B(new_n133_), .C(i_2_), .D(new_n52_), .Y(new_n140_));
  NA4        g130(.A(new_n140_), .B(new_n139_), .C(new_n136_), .D(new_n134_), .Y(new_n141_));
  NO3        g131(.A(i_0_), .B(new_n48_), .C(new_n21_), .Y(new_n142_));
  NO3        g132(.A(i_4_), .B(new_n107_), .C(i_7_), .Y(new_n143_));
  AOI220     g133(.A0(new_n143_), .A1(new_n142_), .B0(new_n122_), .B1(new_n75_), .Y(new_n144_));
  NO2        g134(.A(new_n22_), .B(i_6_), .Y(new_n145_));
  NA4        g135(.A(new_n145_), .B(new_n40_), .C(i_2_), .D(new_n48_), .Y(new_n146_));
  NO3        g136(.A(new_n52_), .B(i_5_), .C(new_n92_), .Y(new_n147_));
  NA3        g137(.A(new_n147_), .B(new_n36_), .C(new_n40_), .Y(new_n148_));
  NA3        g138(.A(new_n148_), .B(new_n146_), .C(new_n144_), .Y(new_n149_));
  NO4        g139(.A(new_n149_), .B(new_n141_), .C(new_n132_), .D(new_n117_), .Y(new_n150_));
  NA2        g140(.A(i_0_), .B(new_n61_), .Y(new_n151_));
  NA3        g141(.A(new_n80_), .B(new_n48_), .C(i_6_), .Y(new_n152_));
  AOI210     g142(.A0(new_n152_), .A1(new_n50_), .B0(new_n151_), .Y(new_n153_));
  INV        g143(.A(new_n44_), .Y(new_n154_));
  NA2        g144(.A(new_n28_), .B(new_n11_), .Y(new_n155_));
  NO3        g145(.A(new_n59_), .B(i_2_), .C(new_n107_), .Y(new_n156_));
  NO2        g146(.A(new_n156_), .B(new_n43_), .Y(new_n157_));
  AOI210     g147(.A0(new_n157_), .A1(new_n155_), .B0(new_n154_), .Y(new_n158_));
  NA2        g148(.A(new_n56_), .B(new_n12_), .Y(new_n159_));
  AOI210     g149(.A0(new_n159_), .A1(i_2_), .B0(new_n142_), .Y(new_n160_));
  NO3        g150(.A(new_n160_), .B(new_n34_), .C(i_5_), .Y(new_n161_));
  NO2        g151(.A(new_n125_), .B(new_n52_), .Y(new_n162_));
  AOI210     g152(.A0(new_n75_), .A1(i_8_), .B0(new_n162_), .Y(new_n163_));
  NO3        g153(.A(new_n163_), .B(new_n61_), .C(i_5_), .Y(new_n164_));
  NO4        g154(.A(new_n164_), .B(new_n161_), .C(new_n158_), .D(new_n153_), .Y(new_n165_));
  NO2        g155(.A(new_n12_), .B(i_2_), .Y(new_n166_));
  NA4        g156(.A(new_n166_), .B(i_4_), .C(new_n52_), .D(i_5_), .Y(new_n167_));
  NO2        g157(.A(new_n59_), .B(i_6_), .Y(new_n168_));
  NA3        g158(.A(new_n168_), .B(new_n135_), .C(i_2_), .Y(new_n169_));
  NAi31      g159(.An(new_n13_), .B(new_n159_), .C(new_n28_), .Y(new_n170_));
  OR3        g160(.A(new_n100_), .B(new_n78_), .C(i_8_), .Y(new_n171_));
  NA4        g161(.A(new_n171_), .B(new_n170_), .C(new_n169_), .D(new_n167_), .Y(new_n172_));
  NA2        g162(.A(new_n168_), .B(new_n65_), .Y(new_n173_));
  NO3        g163(.A(new_n40_), .B(i_2_), .C(i_1_), .Y(new_n174_));
  NA3        g164(.A(new_n174_), .B(new_n138_), .C(i_5_), .Y(new_n175_));
  OR3        g165(.A(new_n128_), .B(new_n108_), .C(i_6_), .Y(new_n176_));
  NO2        g166(.A(new_n48_), .B(i_4_), .Y(new_n177_));
  NA4        g167(.A(new_n177_), .B(new_n31_), .C(i_5_), .D(new_n92_), .Y(new_n178_));
  NA4        g168(.A(new_n178_), .B(new_n176_), .C(new_n175_), .D(new_n173_), .Y(new_n179_));
  NA3        g169(.A(new_n143_), .B(new_n36_), .C(i_0_), .Y(new_n180_));
  NA4        g170(.A(new_n138_), .B(new_n133_), .C(i_2_), .D(new_n52_), .Y(new_n181_));
  NA2        g171(.A(new_n174_), .B(new_n23_), .Y(new_n182_));
  NA3        g172(.A(new_n182_), .B(new_n181_), .C(new_n180_), .Y(new_n183_));
  OAI210     g173(.A0(new_n137_), .A1(new_n41_), .B0(new_n45_), .Y(new_n184_));
  NA2        g174(.A(new_n47_), .B(new_n13_), .Y(new_n185_));
  NA4        g175(.A(new_n185_), .B(new_n11_), .C(new_n61_), .D(i_6_), .Y(new_n186_));
  NA2        g176(.A(new_n147_), .B(new_n29_), .Y(new_n187_));
  NA3        g177(.A(new_n177_), .B(new_n84_), .C(new_n137_), .Y(new_n188_));
  NA4        g178(.A(new_n188_), .B(new_n187_), .C(new_n186_), .D(new_n184_), .Y(new_n189_));
  NO4        g179(.A(new_n189_), .B(new_n183_), .C(new_n179_), .D(new_n172_), .Y(new_n190_));
  NA4        g180(.A(new_n190_), .B(new_n165_), .C(new_n150_), .D(new_n89_), .Y(o_0_));
endmodule


