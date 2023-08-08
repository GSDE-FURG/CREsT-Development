// Benchmark "teste2-ESPRESSO2" written by ABC on Fri Jul 28 06:00:24 2023

module \teste2-ESPRESSO2  ( 
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
    new_n150_, new_n151_;
  INV        g000(.A(i_7_), .Y(new_n11_));
  INV        g001(.A(i_3_), .Y(new_n12_));
  NA2        g002(.A(i_2_), .B(new_n12_), .Y(new_n13_));
  INV        g003(.A(i_5_), .Y(new_n14_));
  NA2        g004(.A(i_3_), .B(new_n14_), .Y(new_n15_));
  AOI210     g005(.A0(new_n15_), .A1(new_n13_), .B0(i_8_), .Y(new_n16_));
  INV        g006(.A(i_8_), .Y(new_n17_));
  NO3        g007(.A(i_3_), .B(i_5_), .C(new_n17_), .Y(new_n18_));
  OAI210     g008(.A0(new_n18_), .A1(new_n16_), .B0(i_4_), .Y(new_n19_));
  INV        g009(.A(i_4_), .Y(new_n20_));
  INV        g010(.A(i_0_), .Y(new_n21_));
  INV        g011(.A(i_2_), .Y(new_n22_));
  NAi21      g012(.An(i_6_), .B(i_0_), .Y(new_n23_));
  NO2        g013(.A(new_n23_), .B(new_n22_), .Y(new_n24_));
  NA2        g014(.A(i_2_), .B(i_3_), .Y(new_n25_));
  NOi21      g015(.An(i_5_), .B(i_2_), .Y(new_n26_));
  NOi21      g016(.An(i_2_), .B(i_4_), .Y(new_n27_));
  OAI210     g017(.A0(new_n27_), .A1(new_n26_), .B0(i_6_), .Y(new_n28_));
  OAI210     g018(.A0(new_n25_), .A1(i_6_), .B0(new_n28_), .Y(new_n29_));
  AOI220     g019(.A0(new_n29_), .A1(new_n21_), .B0(new_n24_), .B1(new_n20_), .Y(new_n30_));
  AO210      g020(.A0(new_n30_), .A1(new_n19_), .B0(new_n11_), .Y(new_n31_));
  NAi21      g021(.An(i_2_), .B(i_3_), .Y(new_n32_));
  NAi21      g022(.An(i_5_), .B(i_6_), .Y(new_n33_));
  AOI210     g023(.A0(new_n33_), .A1(new_n32_), .B0(i_4_), .Y(new_n34_));
  NAi21      g024(.An(i_5_), .B(i_2_), .Y(new_n35_));
  NO2        g025(.A(new_n35_), .B(i_0_), .Y(new_n36_));
  OAI210     g026(.A0(new_n36_), .A1(new_n34_), .B0(i_7_), .Y(new_n37_));
  NAi31      g027(.An(i_6_), .B(i_0_), .C(i_2_), .Y(new_n38_));
  OAI210     g028(.A0(new_n32_), .A1(new_n20_), .B0(new_n38_), .Y(new_n39_));
  NAi21      g029(.An(i_3_), .B(i_5_), .Y(new_n40_));
  NAi21      g030(.An(i_0_), .B(i_6_), .Y(new_n41_));
  AOI210     g031(.A0(new_n41_), .A1(new_n23_), .B0(new_n40_), .Y(new_n42_));
  AOI210     g032(.A0(new_n39_), .A1(new_n14_), .B0(new_n42_), .Y(new_n43_));
  AOI210     g033(.A0(new_n43_), .A1(new_n37_), .B0(new_n17_), .Y(new_n44_));
  INV        g034(.A(i_6_), .Y(new_n45_));
  NAi21      g035(.An(i_0_), .B(i_3_), .Y(new_n46_));
  NAi21      g036(.An(i_2_), .B(i_4_), .Y(new_n47_));
  AOI210     g037(.A0(new_n47_), .A1(new_n46_), .B0(new_n45_), .Y(new_n48_));
  OAI210     g038(.A0(new_n48_), .A1(new_n24_), .B0(new_n17_), .Y(new_n49_));
  NAi31      g039(.An(i_2_), .B(i_3_), .C(i_6_), .Y(new_n50_));
  NAi21      g040(.An(i_2_), .B(i_0_), .Y(new_n51_));
  NAi31      g041(.An(new_n51_), .B(new_n50_), .C(i_4_), .Y(new_n52_));
  AOI210     g042(.A0(new_n52_), .A1(new_n49_), .B0(new_n14_), .Y(new_n53_));
  NAi21      g043(.An(i_4_), .B(i_6_), .Y(new_n54_));
  NA2        g044(.A(i_0_), .B(new_n12_), .Y(new_n55_));
  OR2        g045(.A(new_n25_), .B(i_8_), .Y(new_n56_));
  AOI210     g046(.A0(new_n56_), .A1(new_n55_), .B0(new_n54_), .Y(new_n57_));
  NO3        g047(.A(new_n57_), .B(new_n53_), .C(new_n44_), .Y(new_n58_));
  AOI210     g048(.A0(new_n58_), .A1(new_n31_), .B0(i_1_), .Y(new_n59_));
  INV        g049(.A(i_1_), .Y(new_n60_));
  NAi21      g050(.An(i_2_), .B(i_5_), .Y(new_n61_));
  NAi21      g051(.An(i_5_), .B(i_4_), .Y(new_n62_));
  AOI210     g052(.A0(new_n62_), .A1(new_n61_), .B0(new_n60_), .Y(new_n63_));
  NAi21      g053(.An(i_1_), .B(i_4_), .Y(new_n64_));
  NAi21      g054(.An(i_4_), .B(i_3_), .Y(new_n65_));
  AOI210     g055(.A0(new_n65_), .A1(new_n64_), .B0(new_n14_), .Y(new_n66_));
  OAI210     g056(.A0(new_n66_), .A1(new_n63_), .B0(new_n45_), .Y(new_n67_));
  NAi31      g057(.An(i_6_), .B(i_4_), .C(i_3_), .Y(new_n68_));
  AOI210     g058(.A0(new_n68_), .A1(new_n33_), .B0(i_8_), .Y(new_n69_));
  NO3        g059(.A(i_2_), .B(i_3_), .C(new_n45_), .Y(new_n70_));
  NOi31      g060(.An(i_4_), .B(i_1_), .C(i_3_), .Y(new_n71_));
  NOi31      g061(.An(i_3_), .B(i_2_), .C(i_5_), .Y(new_n72_));
  OA210      g062(.A0(new_n72_), .A1(new_n71_), .B0(i_8_), .Y(new_n73_));
  NO3        g063(.A(new_n73_), .B(new_n70_), .C(new_n69_), .Y(new_n74_));
  AOI210     g064(.A0(new_n74_), .A1(new_n67_), .B0(new_n21_), .Y(new_n75_));
  NAi21      g065(.An(i_1_), .B(i_8_), .Y(new_n76_));
  AOI210     g066(.A0(new_n76_), .A1(new_n35_), .B0(new_n45_), .Y(new_n77_));
  OAI220     g067(.A0(new_n61_), .A1(new_n60_), .B0(new_n25_), .B1(i_6_), .Y(new_n78_));
  OAI210     g068(.A0(new_n78_), .A1(new_n77_), .B0(new_n21_), .Y(new_n79_));
  NAi31      g069(.An(i_3_), .B(i_2_), .C(i_5_), .Y(new_n80_));
  AOI210     g070(.A0(new_n80_), .A1(new_n50_), .B0(i_8_), .Y(new_n81_));
  NOi31      g071(.An(i_2_), .B(i_1_), .C(i_5_), .Y(new_n82_));
  NOi31      g072(.An(i_5_), .B(i_3_), .C(i_6_), .Y(new_n83_));
  OA210      g073(.A0(new_n83_), .A1(new_n82_), .B0(i_8_), .Y(new_n84_));
  NAi21      g074(.An(i_1_), .B(i_3_), .Y(new_n85_));
  NAi21      g075(.An(i_3_), .B(i_1_), .Y(new_n86_));
  AOI220     g076(.A0(new_n86_), .A1(new_n61_), .B0(new_n85_), .B1(new_n35_), .Y(new_n87_));
  NO3        g077(.A(new_n87_), .B(new_n84_), .C(new_n81_), .Y(new_n88_));
  AOI210     g078(.A0(new_n88_), .A1(new_n79_), .B0(new_n20_), .Y(new_n89_));
  NAi21      g079(.An(i_0_), .B(i_2_), .Y(new_n90_));
  AOI210     g080(.A0(new_n90_), .A1(new_n32_), .B0(new_n45_), .Y(new_n91_));
  OAI220     g081(.A0(new_n85_), .A1(new_n22_), .B0(new_n40_), .B1(new_n60_), .Y(new_n92_));
  OAI210     g082(.A0(new_n92_), .A1(new_n91_), .B0(new_n20_), .Y(new_n93_));
  OR2        g083(.A(new_n86_), .B(new_n41_), .Y(new_n94_));
  AOI210     g084(.A0(new_n94_), .A1(new_n93_), .B0(new_n17_), .Y(new_n95_));
  AOI210     g085(.A0(new_n85_), .A1(new_n54_), .B0(new_n14_), .Y(new_n96_));
  NO2        g086(.A(new_n46_), .B(new_n60_), .Y(new_n97_));
  OAI210     g087(.A0(new_n97_), .A1(new_n96_), .B0(new_n17_), .Y(new_n98_));
  OR3        g088(.A(new_n33_), .B(new_n60_), .C(i_4_), .Y(new_n99_));
  AOI210     g089(.A0(new_n99_), .A1(new_n98_), .B0(new_n22_), .Y(new_n100_));
  NO4        g090(.A(new_n100_), .B(new_n95_), .C(new_n89_), .D(new_n75_), .Y(new_n101_));
  NO2        g091(.A(new_n101_), .B(i_7_), .Y(new_n102_));
  NA2        g092(.A(i_2_), .B(new_n20_), .Y(new_n103_));
  NAi21      g093(.An(i_0_), .B(i_4_), .Y(new_n104_));
  AO210      g094(.A0(new_n104_), .A1(new_n103_), .B0(new_n17_), .Y(new_n105_));
  NO2        g095(.A(i_8_), .B(new_n11_), .Y(new_n106_));
  NA2        g096(.A(new_n106_), .B(i_2_), .Y(new_n107_));
  AOI210     g097(.A0(new_n107_), .A1(new_n105_), .B0(i_3_), .Y(new_n108_));
  AOI210     g098(.A0(new_n51_), .A1(new_n46_), .B0(new_n11_), .Y(new_n109_));
  NO3        g099(.A(new_n21_), .B(i_4_), .C(new_n12_), .Y(new_n110_));
  OAI210     g100(.A0(new_n110_), .A1(new_n109_), .B0(new_n45_), .Y(new_n111_));
  OAI210     g101(.A0(new_n41_), .A1(new_n11_), .B0(new_n38_), .Y(new_n112_));
  AOI210     g102(.A0(new_n54_), .A1(new_n47_), .B0(new_n46_), .Y(new_n113_));
  AOI210     g103(.A0(new_n112_), .A1(new_n17_), .B0(new_n113_), .Y(new_n114_));
  NA2        g104(.A(new_n114_), .B(new_n111_), .Y(new_n115_));
  OAI210     g105(.A0(new_n115_), .A1(new_n108_), .B0(i_1_), .Y(new_n116_));
  NO2        g106(.A(new_n21_), .B(i_3_), .Y(new_n117_));
  NA3        g107(.A(new_n27_), .B(i_0_), .C(new_n17_), .Y(new_n118_));
  NAi21      g108(.An(i_2_), .B(i_7_), .Y(new_n119_));
  AOI220     g109(.A0(i_0_), .A1(new_n20_), .B0(i_6_), .B1(new_n17_), .Y(new_n120_));
  OAI210     g110(.A0(new_n120_), .A1(new_n119_), .B0(new_n118_), .Y(new_n121_));
  NA3        g111(.A(i_2_), .B(i_4_), .C(new_n45_), .Y(new_n122_));
  OAI210     g112(.A0(new_n119_), .A1(new_n17_), .B0(new_n122_), .Y(new_n123_));
  AOI220     g113(.A0(new_n123_), .A1(new_n117_), .B0(new_n121_), .B1(i_3_), .Y(new_n124_));
  AOI210     g114(.A0(new_n124_), .A1(new_n116_), .B0(i_5_), .Y(new_n125_));
  OAI210     g115(.A0(new_n21_), .A1(i_8_), .B0(new_n104_), .Y(new_n126_));
  AOI220     g116(.A0(new_n126_), .A1(i_7_), .B0(new_n117_), .B1(i_4_), .Y(new_n127_));
  AN2        g117(.A(new_n90_), .B(new_n65_), .Y(new_n128_));
  AOI210     g118(.A0(new_n25_), .A1(i_8_), .B0(new_n106_), .Y(new_n129_));
  OAI220     g119(.A0(new_n129_), .A1(new_n128_), .B0(new_n127_), .B1(i_2_), .Y(new_n130_));
  OR2        g120(.A(new_n86_), .B(i_8_), .Y(new_n131_));
  NAi21      g121(.An(i_4_), .B(i_8_), .Y(new_n132_));
  OAI220     g122(.A0(new_n132_), .A1(i_2_), .B0(new_n86_), .B1(i_8_), .Y(new_n133_));
  NA2        g123(.A(i_3_), .B(i_8_), .Y(new_n134_));
  NO2        g124(.A(i_3_), .B(i_6_), .Y(new_n135_));
  NO3        g125(.A(new_n135_), .B(new_n60_), .C(i_4_), .Y(new_n136_));
  AOI220     g126(.A0(new_n136_), .A1(new_n134_), .B0(new_n133_), .B1(i_7_), .Y(new_n137_));
  OAI220     g127(.A0(new_n137_), .A1(i_0_), .B0(new_n131_), .B1(new_n103_), .Y(new_n138_));
  AOI210     g128(.A0(new_n130_), .A1(new_n45_), .B0(new_n138_), .Y(new_n139_));
  NO3        g129(.A(new_n47_), .B(new_n45_), .C(i_8_), .Y(new_n140_));
  NO3        g130(.A(new_n132_), .B(new_n12_), .C(i_6_), .Y(new_n141_));
  OAI210     g131(.A0(new_n141_), .A1(new_n140_), .B0(new_n21_), .Y(new_n142_));
  AOI210     g132(.A0(new_n132_), .A1(new_n104_), .B0(new_n119_), .Y(new_n143_));
  NO2        g133(.A(new_n132_), .B(new_n23_), .Y(new_n144_));
  OAI210     g134(.A0(new_n144_), .A1(new_n143_), .B0(new_n12_), .Y(new_n145_));
  AOI210     g135(.A0(new_n145_), .A1(new_n142_), .B0(new_n60_), .Y(new_n146_));
  OA210      g136(.A0(i_2_), .A1(new_n45_), .B0(new_n23_), .Y(new_n147_));
  NA3        g137(.A(i_4_), .B(new_n12_), .C(i_7_), .Y(new_n148_));
  OAI220     g138(.A0(new_n148_), .A1(new_n147_), .B0(new_n54_), .B1(new_n51_), .Y(new_n149_));
  AOI210     g139(.A0(new_n149_), .A1(new_n17_), .B0(new_n146_), .Y(new_n150_));
  OAI210     g140(.A0(new_n139_), .A1(new_n14_), .B0(new_n150_), .Y(new_n151_));
  OR4        g141(.A(new_n151_), .B(new_n125_), .C(new_n102_), .D(new_n59_), .Y(o_0_));
endmodule


