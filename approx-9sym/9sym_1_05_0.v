// Benchmark "9sym/9sym_1_5_0_esp" written by ABC on Wed Mar  8 02:28:22 2023

module \9sym/9sym_1_5_0_esp  ( 
    i0, i1, i2, i3, i4, i5, i6, i7, i8,
    zori0  );
  input  i0, i1, i2, i3, i4, i5, i6, i7, i8;
  output zori0;
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
  INV        g000(.A(i6), .Y(new_n11_));
  INV        g001(.A(i0), .Y(new_n12_));
  INV        g002(.A(i8), .Y(new_n13_));
  NA2        g003(.A(new_n13_), .B(i1), .Y(new_n14_));
  NO2        g004(.A(new_n14_), .B(i7), .Y(new_n15_));
  INV        g005(.A(i5), .Y(new_n16_));
  OR2        g006(.A(i3), .B(i1), .Y(new_n17_));
  NO2        g007(.A(new_n17_), .B(new_n16_), .Y(new_n18_));
  OA210      g008(.A0(new_n18_), .A1(new_n15_), .B0(new_n12_), .Y(new_n19_));
  NOi21      g009(.An(i3), .B(i7), .Y(new_n20_));
  NA2        g010(.A(new_n20_), .B(new_n13_), .Y(new_n21_));
  NAi21      g011(.An(i4), .B(i8), .Y(new_n22_));
  OA210      g012(.A0(new_n22_), .A1(i5), .B0(new_n21_), .Y(new_n23_));
  INV        g013(.A(i4), .Y(new_n24_));
  NO2        g014(.A(i5), .B(i3), .Y(new_n25_));
  NA3        g015(.A(new_n25_), .B(new_n24_), .C(i0), .Y(new_n26_));
  OAI210     g016(.A0(new_n23_), .A1(i1), .B0(new_n26_), .Y(new_n27_));
  OAI210     g017(.A0(new_n27_), .A1(new_n19_), .B0(i2), .Y(new_n28_));
  INV        g018(.A(i7), .Y(new_n29_));
  NO2        g019(.A(i8), .B(i4), .Y(new_n30_));
  OAI210     g020(.A0(i5), .A1(i2), .B0(i1), .Y(new_n31_));
  INV        g021(.A(i1), .Y(new_n32_));
  NAi21      g022(.An(i2), .B(i8), .Y(new_n33_));
  AOI210     g023(.A0(new_n33_), .A1(new_n32_), .B0(i3), .Y(new_n34_));
  OAI210     g024(.A0(new_n31_), .A1(new_n30_), .B0(new_n34_), .Y(new_n35_));
  INV        g025(.A(i2), .Y(new_n36_));
  NAi21      g026(.An(i2), .B(i3), .Y(new_n37_));
  OAI220     g027(.A0(new_n37_), .A1(i4), .B0(i5), .B1(new_n36_), .Y(new_n38_));
  NO2        g028(.A(i8), .B(i5), .Y(new_n39_));
  NOi21      g029(.An(i5), .B(i2), .Y(new_n40_));
  NO2        g030(.A(i4), .B(i1), .Y(new_n41_));
  AO220      g031(.A0(new_n41_), .A1(new_n40_), .B0(new_n39_), .B1(i0), .Y(new_n42_));
  AOI210     g032(.A0(new_n38_), .A1(new_n12_), .B0(new_n42_), .Y(new_n43_));
  AOI210     g033(.A0(new_n43_), .A1(new_n35_), .B0(new_n29_), .Y(new_n44_));
  NAi21      g034(.An(i3), .B(i8), .Y(new_n45_));
  NO2        g035(.A(new_n45_), .B(i2), .Y(new_n46_));
  NO3        g036(.A(i8), .B(i1), .C(new_n12_), .Y(new_n47_));
  OAI210     g037(.A0(new_n47_), .A1(new_n46_), .B0(i5), .Y(new_n48_));
  INV        g038(.A(i3), .Y(new_n49_));
  NO2        g039(.A(i5), .B(new_n49_), .Y(new_n50_));
  NAi21      g040(.An(i7), .B(i8), .Y(new_n51_));
  OAI210     g041(.A0(i2), .A1(new_n32_), .B0(new_n51_), .Y(new_n52_));
  NAi21      g042(.An(new_n40_), .B(new_n45_), .Y(new_n53_));
  NO2        g043(.A(i7), .B(new_n32_), .Y(new_n54_));
  AOI220     g044(.A0(new_n54_), .A1(new_n53_), .B0(new_n52_), .B1(new_n50_), .Y(new_n55_));
  AOI210     g045(.A0(new_n55_), .A1(new_n48_), .B0(i4), .Y(new_n56_));
  NOi21      g046(.An(i8), .B(i2), .Y(new_n57_));
  OAI210     g047(.A0(new_n50_), .A1(new_n57_), .B0(new_n29_), .Y(new_n58_));
  OAI210     g048(.A0(i8), .A1(i5), .B0(i1), .Y(new_n59_));
  NA2        g049(.A(new_n59_), .B(i0), .Y(new_n60_));
  AOI210     g050(.A0(new_n58_), .A1(new_n32_), .B0(new_n60_), .Y(new_n61_));
  NO3        g051(.A(i8), .B(i1), .C(i0), .Y(new_n62_));
  AOI210     g052(.A0(new_n25_), .A1(new_n29_), .B0(new_n62_), .Y(new_n63_));
  NAi21      g053(.An(i8), .B(i5), .Y(new_n64_));
  NA2        g054(.A(new_n20_), .B(new_n36_), .Y(new_n65_));
  OAI220     g055(.A0(new_n65_), .A1(new_n64_), .B0(new_n63_), .B1(new_n24_), .Y(new_n66_));
  NO4        g056(.A(new_n66_), .B(new_n61_), .C(new_n56_), .D(new_n44_), .Y(new_n67_));
  AOI210     g057(.A0(new_n67_), .A1(new_n28_), .B0(new_n11_), .Y(new_n68_));
  NA2        g058(.A(i2), .B(i0), .Y(new_n69_));
  AOI220     g059(.A0(new_n30_), .A1(i2), .B0(i7), .B1(new_n12_), .Y(new_n70_));
  NAi21      g060(.An(new_n40_), .B(new_n22_), .Y(new_n71_));
  NA3        g061(.A(new_n71_), .B(new_n29_), .C(i0), .Y(new_n72_));
  OAI210     g062(.A0(new_n70_), .A1(new_n16_), .B0(new_n72_), .Y(new_n73_));
  NA3        g063(.A(new_n69_), .B(i7), .C(i5), .Y(new_n74_));
  AOI210     g064(.A0(new_n45_), .A1(new_n36_), .B0(new_n74_), .Y(new_n75_));
  AOI210     g065(.A0(new_n73_), .A1(i3), .B0(new_n75_), .Y(new_n76_));
  NA2        g066(.A(new_n30_), .B(new_n20_), .Y(new_n77_));
  OAI220     g067(.A0(new_n77_), .A1(new_n69_), .B0(new_n76_), .B1(i6), .Y(new_n78_));
  NA2        g068(.A(new_n16_), .B(i3), .Y(new_n79_));
  AOI210     g069(.A0(new_n79_), .A1(new_n14_), .B0(i2), .Y(new_n80_));
  NO2        g070(.A(new_n51_), .B(i5), .Y(new_n81_));
  OAI210     g071(.A0(new_n81_), .A1(new_n80_), .B0(i0), .Y(new_n82_));
  NA2        g072(.A(new_n13_), .B(i7), .Y(new_n83_));
  OAI210     g073(.A0(new_n69_), .A1(i7), .B0(new_n83_), .Y(new_n84_));
  NAi21      g074(.An(i5), .B(i7), .Y(new_n85_));
  AOI210     g075(.A0(new_n85_), .A1(new_n21_), .B0(new_n50_), .Y(new_n86_));
  AOI210     g076(.A0(new_n84_), .A1(new_n32_), .B0(new_n86_), .Y(new_n87_));
  AOI210     g077(.A0(new_n87_), .A1(new_n82_), .B0(i6), .Y(new_n88_));
  NO2        g078(.A(new_n13_), .B(i7), .Y(new_n89_));
  NA2        g079(.A(new_n29_), .B(i5), .Y(new_n90_));
  NO2        g080(.A(new_n13_), .B(new_n36_), .Y(new_n91_));
  OAI220     g081(.A0(new_n91_), .A1(new_n59_), .B0(new_n90_), .B1(new_n36_), .Y(new_n92_));
  AOI220     g082(.A0(new_n92_), .A1(new_n12_), .B0(new_n89_), .B1(new_n40_), .Y(new_n93_));
  AOI220     g083(.A0(i8), .A1(i2), .B0(i3), .B1(i1), .Y(new_n94_));
  NOi21      g084(.An(i2), .B(i8), .Y(new_n95_));
  AOI220     g085(.A0(new_n95_), .A1(new_n17_), .B0(new_n57_), .B1(new_n20_), .Y(new_n96_));
  OAI210     g086(.A0(new_n94_), .A1(i0), .B0(new_n96_), .Y(new_n97_));
  AOI220     g087(.A0(new_n97_), .A1(new_n16_), .B0(new_n47_), .B1(new_n40_), .Y(new_n98_));
  OAI210     g088(.A0(new_n93_), .A1(i3), .B0(new_n98_), .Y(new_n99_));
  OA210      g089(.A0(new_n99_), .A1(new_n88_), .B0(i4), .Y(new_n100_));
  OAI210     g090(.A0(new_n95_), .A1(new_n89_), .B0(i0), .Y(new_n101_));
  NO2        g091(.A(new_n29_), .B(i4), .Y(new_n102_));
  NA2        g092(.A(new_n102_), .B(i2), .Y(new_n103_));
  AOI210     g093(.A0(new_n103_), .A1(new_n101_), .B0(i6), .Y(new_n104_));
  NA2        g094(.A(new_n13_), .B(i0), .Y(new_n105_));
  NA2        g095(.A(i7), .B(new_n36_), .Y(new_n106_));
  AOI210     g096(.A0(new_n106_), .A1(new_n90_), .B0(new_n105_), .Y(new_n107_));
  OAI210     g097(.A0(new_n107_), .A1(new_n104_), .B0(new_n49_), .Y(new_n108_));
  OAI210     g098(.A0(new_n16_), .A1(i3), .B0(new_n51_), .Y(new_n109_));
  NO2        g099(.A(new_n64_), .B(new_n49_), .Y(new_n110_));
  AOI210     g100(.A0(new_n109_), .A1(i2), .B0(new_n110_), .Y(new_n111_));
  NO2        g101(.A(new_n49_), .B(i2), .Y(new_n112_));
  NAi21      g102(.An(i6), .B(i5), .Y(new_n113_));
  NO2        g103(.A(new_n113_), .B(new_n13_), .Y(new_n114_));
  XO2        g104(.A(i8), .B(i7), .Y(new_n115_));
  AOI210     g105(.A0(new_n115_), .A1(new_n112_), .B0(new_n114_), .Y(new_n116_));
  OAI210     g106(.A0(new_n111_), .A1(i4), .B0(new_n116_), .Y(new_n117_));
  OA220      g107(.A0(new_n113_), .A1(new_n29_), .B0(new_n105_), .B1(new_n49_), .Y(new_n118_));
  NA2        g108(.A(new_n24_), .B(new_n36_), .Y(new_n119_));
  NAi21      g109(.An(i8), .B(i2), .Y(new_n120_));
  NAi21      g110(.An(i6), .B(i7), .Y(new_n121_));
  OAI220     g111(.A0(new_n121_), .A1(new_n22_), .B0(new_n120_), .B1(new_n49_), .Y(new_n122_));
  AOI210     g112(.A0(new_n122_), .A1(new_n16_), .B0(new_n32_), .Y(new_n123_));
  OAI210     g113(.A0(new_n119_), .A1(new_n118_), .B0(new_n123_), .Y(new_n124_));
  AOI210     g114(.A0(new_n117_), .A1(new_n12_), .B0(new_n124_), .Y(new_n125_));
  OAI210     g115(.A0(new_n29_), .A1(i3), .B0(new_n51_), .Y(new_n126_));
  NA2        g116(.A(new_n126_), .B(i5), .Y(new_n127_));
  OR2        g117(.A(new_n121_), .B(new_n49_), .Y(new_n128_));
  AOI210     g118(.A0(new_n128_), .A1(new_n127_), .B0(i4), .Y(new_n129_));
  NO2        g119(.A(new_n121_), .B(new_n33_), .Y(new_n130_));
  OAI210     g120(.A0(new_n130_), .A1(new_n129_), .B0(i0), .Y(new_n131_));
  AOI210     g121(.A0(new_n64_), .A1(new_n51_), .B0(new_n12_), .Y(new_n132_));
  AOI210     g122(.A0(new_n113_), .A1(new_n85_), .B0(new_n13_), .Y(new_n133_));
  OAI210     g123(.A0(new_n133_), .A1(new_n132_), .B0(new_n49_), .Y(new_n134_));
  NO2        g124(.A(new_n29_), .B(i5), .Y(new_n135_));
  NA3        g125(.A(new_n135_), .B(new_n11_), .C(i0), .Y(new_n136_));
  NO2        g126(.A(new_n49_), .B(i0), .Y(new_n137_));
  OAI210     g127(.A0(new_n102_), .A1(new_n89_), .B0(new_n137_), .Y(new_n138_));
  NA3        g128(.A(new_n138_), .B(new_n136_), .C(new_n134_), .Y(new_n139_));
  AOI210     g129(.A0(i5), .A1(new_n12_), .B0(new_n135_), .Y(new_n140_));
  NA2        g130(.A(new_n112_), .B(i8), .Y(new_n141_));
  OAI210     g131(.A0(new_n141_), .A1(new_n140_), .B0(new_n32_), .Y(new_n142_));
  AOI210     g132(.A0(new_n139_), .A1(i2), .B0(new_n142_), .Y(new_n143_));
  AOI220     g133(.A0(new_n143_), .A1(new_n131_), .B0(new_n125_), .B1(new_n108_), .Y(new_n144_));
  OR4        g134(.A(new_n144_), .B(new_n100_), .C(new_n78_), .D(new_n68_), .Y(zori0));
endmodule


