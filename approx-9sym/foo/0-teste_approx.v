// Benchmark "teste2-ESPRESSO2" written by ABC on Fri Jul 28 05:34:39 2023

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
    new_n144_;
  NOi21      g000(.An(i_6_), .B(i_0_), .Y(new_n11_));
  NA2        g001(.A(new_n11_), .B(i_3_), .Y(new_n12_));
  INV        g002(.A(i_8_), .Y(new_n13_));
  INV        g003(.A(i_0_), .Y(new_n14_));
  NO3        g004(.A(new_n14_), .B(new_n13_), .C(i_7_), .Y(new_n15_));
  NAi21      g005(.An(i_4_), .B(i_3_), .Y(new_n16_));
  OAI210     g006(.A0(i_3_), .A1(new_n13_), .B0(new_n16_), .Y(new_n17_));
  AOI210     g007(.A0(new_n17_), .A1(i_7_), .B0(new_n15_), .Y(new_n18_));
  AOI210     g008(.A0(new_n18_), .A1(new_n12_), .B0(i_1_), .Y(new_n19_));
  INV        g009(.A(i_6_), .Y(new_n20_));
  INV        g010(.A(i_4_), .Y(new_n21_));
  NAi21      g011(.An(i_4_), .B(i_8_), .Y(new_n22_));
  NO2        g012(.A(new_n22_), .B(i_7_), .Y(new_n23_));
  INV        g013(.A(i_7_), .Y(new_n24_));
  NO3        g014(.A(i_0_), .B(i_3_), .C(new_n24_), .Y(new_n25_));
  NAi21      g015(.An(i_4_), .B(i_0_), .Y(new_n26_));
  NAi21      g016(.An(i_0_), .B(i_1_), .Y(new_n27_));
  AOI210     g017(.A0(new_n27_), .A1(new_n26_), .B0(i_8_), .Y(new_n28_));
  NO3        g018(.A(new_n28_), .B(new_n25_), .C(new_n23_), .Y(new_n29_));
  INV        g019(.A(i_1_), .Y(new_n30_));
  OAI220     g020(.A0(new_n14_), .A1(i_7_), .B0(new_n30_), .B1(i_3_), .Y(new_n31_));
  NA2        g021(.A(i_0_), .B(i_3_), .Y(new_n32_));
  NO2        g022(.A(new_n30_), .B(i_7_), .Y(new_n33_));
  AOI220     g023(.A0(new_n33_), .A1(new_n32_), .B0(new_n31_), .B1(new_n13_), .Y(new_n34_));
  OAI220     g024(.A0(new_n34_), .A1(new_n21_), .B0(new_n29_), .B1(new_n20_), .Y(new_n35_));
  OAI210     g025(.A0(new_n35_), .A1(new_n19_), .B0(i_2_), .Y(new_n36_));
  NO2        g026(.A(i_6_), .B(new_n13_), .Y(new_n37_));
  INV        g027(.A(i_3_), .Y(new_n38_));
  NAi21      g028(.An(i_2_), .B(i_0_), .Y(new_n39_));
  NO2        g029(.A(new_n39_), .B(new_n38_), .Y(new_n40_));
  OAI210     g030(.A0(new_n40_), .A1(new_n37_), .B0(new_n24_), .Y(new_n41_));
  NAi21      g031(.An(i_6_), .B(i_3_), .Y(new_n42_));
  NO3        g032(.A(new_n42_), .B(new_n30_), .C(i_8_), .Y(new_n43_));
  OAI220     g033(.A0(i_1_), .A1(i_8_), .B0(i_3_), .B1(i_6_), .Y(new_n44_));
  AOI210     g034(.A0(new_n44_), .A1(i_7_), .B0(new_n43_), .Y(new_n45_));
  AOI210     g035(.A0(new_n45_), .A1(new_n41_), .B0(new_n21_), .Y(new_n46_));
  NAi31      g036(.An(i_2_), .B(i_0_), .C(i_1_), .Y(new_n47_));
  OAI210     g037(.A0(new_n26_), .A1(new_n24_), .B0(new_n47_), .Y(new_n48_));
  AOI210     g038(.A0(new_n27_), .A1(new_n16_), .B0(i_2_), .Y(new_n49_));
  AOI220     g039(.A0(new_n49_), .A1(i_7_), .B0(new_n48_), .B1(new_n38_), .Y(new_n50_));
  NO3        g040(.A(new_n22_), .B(i_0_), .C(new_n24_), .Y(new_n51_));
  OR3        g041(.A(new_n39_), .B(i_1_), .C(new_n13_), .Y(new_n52_));
  NOi21      g042(.An(new_n52_), .B(new_n51_), .Y(new_n53_));
  AOI210     g043(.A0(new_n53_), .A1(new_n50_), .B0(new_n20_), .Y(new_n54_));
  NAi21      g044(.An(i_2_), .B(i_1_), .Y(new_n55_));
  OAI210     g045(.A0(i_1_), .A1(i_3_), .B0(i_8_), .Y(new_n56_));
  AN2        g046(.A(i_1_), .B(i_4_), .Y(new_n57_));
  OAI220     g047(.A0(new_n57_), .A1(new_n56_), .B0(new_n55_), .B1(new_n38_), .Y(new_n58_));
  NO2        g048(.A(new_n47_), .B(i_8_), .Y(new_n59_));
  AOI210     g049(.A0(new_n58_), .A1(new_n20_), .B0(new_n59_), .Y(new_n60_));
  NOi21      g050(.An(i_1_), .B(i_0_), .Y(new_n61_));
  NA3        g051(.A(new_n37_), .B(new_n61_), .C(i_3_), .Y(new_n62_));
  OAI210     g052(.A0(new_n60_), .A1(new_n24_), .B0(new_n62_), .Y(new_n63_));
  NO3        g053(.A(new_n63_), .B(new_n54_), .C(new_n46_), .Y(new_n64_));
  AOI210     g054(.A0(new_n64_), .A1(new_n36_), .B0(i_5_), .Y(new_n65_));
  OAI210     g055(.A0(i_0_), .A1(i_7_), .B0(i_4_), .Y(new_n66_));
  NO2        g056(.A(new_n14_), .B(i_1_), .Y(new_n67_));
  INV        g057(.A(i_5_), .Y(new_n68_));
  NO2        g058(.A(i_6_), .B(new_n68_), .Y(new_n69_));
  OAI210     g059(.A0(new_n69_), .A1(new_n67_), .B0(i_7_), .Y(new_n70_));
  NAi21      g060(.An(i_7_), .B(i_2_), .Y(new_n71_));
  NAi21      g061(.An(new_n11_), .B(new_n71_), .Y(new_n72_));
  NA2        g062(.A(new_n72_), .B(i_5_), .Y(new_n73_));
  NAi31      g063(.An(i_2_), .B(i_1_), .C(i_6_), .Y(new_n74_));
  NA4        g064(.A(new_n74_), .B(new_n73_), .C(new_n70_), .D(new_n21_), .Y(new_n75_));
  NA3        g065(.A(new_n75_), .B(new_n66_), .C(i_8_), .Y(new_n76_));
  NO2        g066(.A(new_n30_), .B(i_8_), .Y(new_n77_));
  NA2        g067(.A(new_n14_), .B(i_2_), .Y(new_n78_));
  NA2        g068(.A(new_n20_), .B(i_7_), .Y(new_n79_));
  NAi21      g069(.An(i_1_), .B(i_4_), .Y(new_n80_));
  AOI220     g070(.A0(new_n80_), .A1(new_n79_), .B0(new_n78_), .B1(new_n39_), .Y(new_n81_));
  NA3        g071(.A(i_1_), .B(i_4_), .C(new_n24_), .Y(new_n82_));
  NOi21      g072(.An(i_2_), .B(i_1_), .Y(new_n83_));
  NA2        g073(.A(new_n83_), .B(i_0_), .Y(new_n84_));
  AOI210     g074(.A0(new_n84_), .A1(new_n82_), .B0(i_6_), .Y(new_n85_));
  OR2        g075(.A(new_n80_), .B(new_n20_), .Y(new_n86_));
  AOI210     g076(.A0(new_n86_), .A1(new_n47_), .B0(i_7_), .Y(new_n87_));
  OAI210     g077(.A0(new_n83_), .A1(new_n61_), .B0(new_n13_), .Y(new_n88_));
  AOI210     g078(.A0(i_0_), .A1(i_8_), .B0(i_2_), .Y(new_n89_));
  OAI210     g079(.A0(i_0_), .A1(i_7_), .B0(new_n89_), .Y(new_n90_));
  AOI210     g080(.A0(new_n90_), .A1(new_n88_), .B0(new_n20_), .Y(new_n91_));
  OR4        g081(.A(new_n91_), .B(new_n87_), .C(new_n85_), .D(new_n81_), .Y(new_n92_));
  INV        g082(.A(i_2_), .Y(new_n93_));
  OAI210     g083(.A0(new_n20_), .A1(new_n24_), .B0(i_4_), .Y(new_n94_));
  AOI210     g084(.A0(new_n94_), .A1(new_n26_), .B0(new_n93_), .Y(new_n95_));
  AOI220     g085(.A0(new_n95_), .A1(new_n77_), .B0(new_n92_), .B1(i_5_), .Y(new_n96_));
  AOI210     g086(.A0(new_n96_), .A1(new_n76_), .B0(i_3_), .Y(new_n97_));
  AOI210     g087(.A0(new_n86_), .A1(new_n82_), .B0(i_2_), .Y(new_n98_));
  NAi21      g088(.An(i_1_), .B(i_5_), .Y(new_n99_));
  NAi21      g089(.An(i_1_), .B(i_2_), .Y(new_n100_));
  AOI210     g090(.A0(new_n100_), .A1(new_n42_), .B0(new_n24_), .Y(new_n101_));
  OAI210     g091(.A0(i_2_), .A1(new_n20_), .B0(new_n71_), .Y(new_n102_));
  AOI210     g092(.A0(new_n102_), .A1(i_3_), .B0(new_n101_), .Y(new_n103_));
  OAI220     g093(.A0(new_n103_), .A1(i_4_), .B0(new_n99_), .B1(new_n42_), .Y(new_n104_));
  OAI210     g094(.A0(new_n104_), .A1(new_n98_), .B0(i_0_), .Y(new_n105_));
  NA2        g095(.A(new_n83_), .B(new_n11_), .Y(new_n106_));
  NA2        g096(.A(i_0_), .B(i_6_), .Y(new_n107_));
  NA3        g097(.A(new_n107_), .B(new_n102_), .C(i_1_), .Y(new_n108_));
  AOI210     g098(.A0(new_n108_), .A1(new_n106_), .B0(new_n21_), .Y(new_n109_));
  NAi21      g099(.An(i_6_), .B(i_2_), .Y(new_n110_));
  OR2        g100(.A(new_n110_), .B(new_n27_), .Y(new_n111_));
  OAI210     g101(.A0(i_2_), .A1(i_6_), .B0(i_4_), .Y(new_n112_));
  OAI210     g102(.A0(new_n11_), .A1(i_4_), .B0(new_n112_), .Y(new_n113_));
  OA220      g103(.A0(new_n113_), .A1(new_n68_), .B0(new_n86_), .B1(i_7_), .Y(new_n114_));
  AOI210     g104(.A0(new_n114_), .A1(new_n111_), .B0(new_n38_), .Y(new_n115_));
  NO2        g105(.A(new_n115_), .B(new_n109_), .Y(new_n116_));
  AOI210     g106(.A0(new_n116_), .A1(new_n105_), .B0(i_8_), .Y(new_n117_));
  OAI210     g107(.A0(new_n99_), .A1(new_n24_), .B0(new_n47_), .Y(new_n118_));
  OAI220     g108(.A0(i_2_), .A1(new_n68_), .B0(i_1_), .B1(new_n13_), .Y(new_n119_));
  NO2        g109(.A(i_2_), .B(i_1_), .Y(new_n120_));
  NO2        g110(.A(new_n120_), .B(i_7_), .Y(new_n121_));
  AOI220     g111(.A0(new_n121_), .A1(new_n119_), .B0(new_n118_), .B1(new_n21_), .Y(new_n122_));
  AOI210     g112(.A0(new_n122_), .A1(new_n52_), .B0(i_6_), .Y(new_n123_));
  NO2        g113(.A(i_2_), .B(new_n68_), .Y(new_n124_));
  NAi21      g114(.An(new_n22_), .B(new_n124_), .Y(new_n125_));
  OAI220     g115(.A0(new_n110_), .A1(new_n68_), .B0(new_n22_), .B1(new_n20_), .Y(new_n126_));
  NA2        g116(.A(new_n80_), .B(new_n74_), .Y(new_n127_));
  AOI220     g117(.A0(new_n127_), .A1(new_n121_), .B0(new_n126_), .B1(new_n30_), .Y(new_n128_));
  OAI220     g118(.A0(new_n128_), .A1(i_0_), .B0(new_n125_), .B1(i_1_), .Y(new_n129_));
  OAI210     g119(.A0(new_n129_), .A1(new_n123_), .B0(i_3_), .Y(new_n130_));
  NO2        g120(.A(i_6_), .B(new_n24_), .Y(new_n131_));
  NO2        g121(.A(new_n93_), .B(i_4_), .Y(new_n132_));
  OAI210     g122(.A0(new_n132_), .A1(new_n124_), .B0(new_n131_), .Y(new_n133_));
  AOI210     g123(.A0(new_n133_), .A1(new_n125_), .B0(new_n30_), .Y(new_n134_));
  NO3        g124(.A(new_n80_), .B(i_2_), .C(new_n24_), .Y(new_n135_));
  OAI210     g125(.A0(new_n135_), .A1(new_n134_), .B0(new_n14_), .Y(new_n136_));
  AOI220     g126(.A0(new_n67_), .A1(i_8_), .B0(new_n61_), .B1(i_2_), .Y(new_n137_));
  AOI210     g127(.A0(new_n110_), .A1(new_n39_), .B0(new_n13_), .Y(new_n138_));
  AOI210     g128(.A0(new_n138_), .A1(i_1_), .B0(i_4_), .Y(new_n139_));
  OAI210     g129(.A0(new_n137_), .A1(new_n68_), .B0(new_n139_), .Y(new_n140_));
  NA2        g130(.A(new_n120_), .B(i_8_), .Y(new_n141_));
  NA3        g131(.A(new_n141_), .B(new_n111_), .C(i_4_), .Y(new_n142_));
  NA3        g132(.A(new_n142_), .B(new_n140_), .C(new_n24_), .Y(new_n143_));
  NA3        g133(.A(new_n143_), .B(new_n136_), .C(new_n130_), .Y(new_n144_));
  OR4        g134(.A(new_n144_), .B(new_n117_), .C(new_n97_), .D(new_n65_), .Y(o_0_));
endmodule


