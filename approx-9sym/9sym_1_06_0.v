// Benchmark "9sym/9sym_1_6_0_esp" written by ABC on Wed Mar  8 02:30:02 2023

module \9sym/9sym_1_6_0_esp  ( 
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
    new_n138_, new_n139_;
  INV        g000(.A(i6), .Y(new_n11_));
  INV        g001(.A(i7), .Y(new_n12_));
  NO2        g002(.A(new_n12_), .B(i0), .Y(new_n13_));
  INV        g003(.A(i8), .Y(new_n14_));
  NO3        g004(.A(new_n14_), .B(i4), .C(i1), .Y(new_n15_));
  OAI210     g005(.A0(new_n15_), .A1(new_n13_), .B0(i2), .Y(new_n16_));
  INV        g006(.A(i3), .Y(new_n17_));
  NO2        g007(.A(i4), .B(new_n17_), .Y(new_n18_));
  INV        g008(.A(i1), .Y(new_n19_));
  NAi21      g009(.An(i7), .B(i8), .Y(new_n20_));
  OAI210     g010(.A0(i2), .A1(new_n19_), .B0(new_n20_), .Y(new_n21_));
  OAI210     g011(.A0(i7), .A1(i3), .B0(i8), .Y(new_n22_));
  AOI220     g012(.A0(new_n22_), .A1(i4), .B0(new_n21_), .B1(new_n18_), .Y(new_n23_));
  AOI210     g013(.A0(new_n23_), .A1(new_n16_), .B0(i5), .Y(new_n24_));
  NO2        g014(.A(new_n14_), .B(i3), .Y(new_n25_));
  INV        g015(.A(i5), .Y(new_n26_));
  NO2        g016(.A(new_n26_), .B(i4), .Y(new_n27_));
  NO2        g017(.A(new_n12_), .B(i1), .Y(new_n28_));
  OAI210     g018(.A0(new_n27_), .A1(new_n25_), .B0(new_n28_), .Y(new_n29_));
  NAi21      g019(.An(i3), .B(i5), .Y(new_n30_));
  OR3        g020(.A(new_n30_), .B(new_n14_), .C(i4), .Y(new_n31_));
  AOI210     g021(.A0(new_n31_), .A1(new_n29_), .B0(i2), .Y(new_n32_));
  NAi21      g022(.An(i4), .B(i7), .Y(new_n33_));
  NO4        g023(.A(new_n33_), .B(i8), .C(i3), .D(new_n19_), .Y(new_n34_));
  INV        g024(.A(i2), .Y(new_n35_));
  INV        g025(.A(i4), .Y(new_n36_));
  OAI220     g026(.A0(new_n30_), .A1(new_n35_), .B0(i8), .B1(new_n36_), .Y(new_n37_));
  NAi21      g027(.An(i2), .B(i3), .Y(new_n38_));
  NAi21      g028(.An(i8), .B(i2), .Y(new_n39_));
  NAi21      g029(.An(i7), .B(i1), .Y(new_n40_));
  OAI220     g030(.A0(new_n40_), .A1(new_n39_), .B0(new_n38_), .B1(new_n33_), .Y(new_n41_));
  AOI210     g031(.A0(new_n37_), .A1(new_n19_), .B0(new_n41_), .Y(new_n42_));
  NAi21      g032(.An(i2), .B(i0), .Y(new_n43_));
  NAi31      g033(.An(i8), .B(i3), .C(i2), .Y(new_n44_));
  AOI210     g034(.A0(new_n44_), .A1(new_n43_), .B0(i1), .Y(new_n45_));
  NAi21      g035(.An(i8), .B(i5), .Y(new_n46_));
  NO2        g036(.A(new_n46_), .B(new_n38_), .Y(new_n47_));
  NAi21      g037(.An(i3), .B(i8), .Y(new_n48_));
  NAi21      g038(.An(i2), .B(i5), .Y(new_n49_));
  NAi21      g039(.An(i4), .B(i1), .Y(new_n50_));
  AOI210     g040(.A0(new_n49_), .A1(new_n48_), .B0(new_n50_), .Y(new_n51_));
  NO3        g041(.A(new_n51_), .B(new_n47_), .C(new_n45_), .Y(new_n52_));
  OAI220     g042(.A0(new_n52_), .A1(i7), .B0(new_n42_), .B1(i0), .Y(new_n53_));
  NO4        g043(.A(new_n53_), .B(new_n34_), .C(new_n32_), .D(new_n24_), .Y(new_n54_));
  NO2        g044(.A(new_n54_), .B(new_n11_), .Y(new_n55_));
  INV        g045(.A(i0), .Y(new_n56_));
  NO2        g046(.A(i6), .B(i3), .Y(new_n57_));
  AOI220     g047(.A0(new_n57_), .A1(i1), .B0(new_n18_), .B1(new_n12_), .Y(new_n58_));
  NAi21      g048(.An(i7), .B(i5), .Y(new_n59_));
  OAI210     g049(.A0(new_n12_), .A1(i2), .B0(new_n59_), .Y(new_n60_));
  NAi21      g050(.An(i4), .B(i3), .Y(new_n61_));
  OAI210     g051(.A0(i6), .A1(new_n36_), .B0(new_n61_), .Y(new_n62_));
  AOI220     g052(.A0(new_n62_), .A1(new_n35_), .B0(new_n60_), .B1(new_n17_), .Y(new_n63_));
  OAI220     g053(.A0(new_n63_), .A1(new_n19_), .B0(new_n58_), .B1(new_n35_), .Y(new_n64_));
  OAI210     g054(.A0(i5), .A1(new_n36_), .B0(new_n59_), .Y(new_n65_));
  NO2        g055(.A(new_n20_), .B(i4), .Y(new_n66_));
  AOI210     g056(.A0(new_n65_), .A1(new_n35_), .B0(new_n66_), .Y(new_n67_));
  AOI220     g057(.A0(new_n26_), .A1(i4), .B0(new_n17_), .B1(i1), .Y(new_n68_));
  OAI220     g058(.A0(new_n68_), .A1(new_n20_), .B0(new_n67_), .B1(new_n17_), .Y(new_n69_));
  AOI220     g059(.A0(new_n69_), .A1(new_n11_), .B0(new_n64_), .B1(new_n14_), .Y(new_n70_));
  NO2        g060(.A(new_n12_), .B(i4), .Y(new_n71_));
  NO2        g061(.A(i5), .B(new_n17_), .Y(new_n72_));
  AOI220     g062(.A0(new_n72_), .A1(new_n14_), .B0(new_n57_), .B1(new_n71_), .Y(new_n73_));
  NAi21      g063(.An(i4), .B(i5), .Y(new_n74_));
  OR2        g064(.A(i5), .B(i3), .Y(new_n75_));
  OAI220     g065(.A0(new_n75_), .A1(new_n14_), .B0(new_n74_), .B1(i6), .Y(new_n76_));
  NO2        g066(.A(new_n14_), .B(i5), .Y(new_n77_));
  NO2        g067(.A(i6), .B(i4), .Y(new_n78_));
  AOI220     g068(.A0(new_n78_), .A1(new_n77_), .B0(new_n76_), .B1(new_n35_), .Y(new_n79_));
  OAI220     g069(.A0(new_n79_), .A1(new_n12_), .B0(new_n73_), .B1(new_n35_), .Y(new_n80_));
  NOi21      g070(.An(i4), .B(i7), .Y(new_n81_));
  NOi21      g071(.An(i2), .B(i4), .Y(new_n82_));
  NOi31      g072(.An(i3), .B(i8), .C(i6), .Y(new_n83_));
  OAI210     g073(.A0(new_n82_), .A1(new_n81_), .B0(new_n83_), .Y(new_n84_));
  NOi21      g074(.An(i7), .B(i6), .Y(new_n85_));
  NOi31      g075(.An(i8), .B(i3), .C(i2), .Y(new_n86_));
  OAI210     g076(.A0(new_n85_), .A1(new_n81_), .B0(new_n86_), .Y(new_n87_));
  AO210      g077(.A0(new_n87_), .A1(new_n84_), .B0(new_n26_), .Y(new_n88_));
  OAI210     g078(.A0(i8), .A1(i1), .B0(new_n75_), .Y(new_n89_));
  NAi21      g079(.An(i6), .B(i7), .Y(new_n90_));
  OAI210     g080(.A0(i4), .A1(new_n56_), .B0(new_n90_), .Y(new_n91_));
  NAi31      g081(.An(new_n78_), .B(new_n91_), .C(new_n89_), .Y(new_n92_));
  NO2        g082(.A(new_n14_), .B(i2), .Y(new_n93_));
  NA3        g083(.A(new_n93_), .B(new_n81_), .C(new_n72_), .Y(new_n94_));
  NA3        g084(.A(new_n94_), .B(new_n92_), .C(new_n88_), .Y(new_n95_));
  AOI210     g085(.A0(new_n80_), .A1(i1), .B0(new_n95_), .Y(new_n96_));
  OAI210     g086(.A0(new_n70_), .A1(new_n56_), .B0(new_n96_), .Y(new_n97_));
  XO2        g087(.A(i8), .B(i7), .Y(new_n98_));
  NO2        g088(.A(new_n46_), .B(i4), .Y(new_n99_));
  AOI210     g089(.A0(new_n98_), .A1(new_n35_), .B0(new_n99_), .Y(new_n100_));
  NO2        g090(.A(new_n100_), .B(new_n19_), .Y(new_n101_));
  NA2        g091(.A(new_n85_), .B(i5), .Y(new_n102_));
  AOI210     g092(.A0(new_n33_), .A1(new_n20_), .B0(new_n35_), .Y(new_n103_));
  AOI210     g093(.A0(new_n93_), .A1(i5), .B0(new_n103_), .Y(new_n104_));
  OAI210     g094(.A0(new_n104_), .A1(i1), .B0(new_n102_), .Y(new_n105_));
  OAI210     g095(.A0(new_n105_), .A1(new_n101_), .B0(i3), .Y(new_n106_));
  NAi21      g096(.An(i2), .B(i8), .Y(new_n107_));
  AOI210     g097(.A0(new_n107_), .A1(new_n46_), .B0(new_n19_), .Y(new_n108_));
  NO2        g098(.A(new_n59_), .B(new_n35_), .Y(new_n109_));
  OAI210     g099(.A0(new_n109_), .A1(new_n108_), .B0(new_n17_), .Y(new_n110_));
  OAI220     g100(.A0(i6), .A1(new_n35_), .B0(i5), .B1(new_n17_), .Y(new_n111_));
  AOI220     g101(.A0(new_n111_), .A1(i1), .B0(new_n77_), .B1(i2), .Y(new_n112_));
  AOI210     g102(.A0(new_n112_), .A1(new_n110_), .B0(new_n36_), .Y(new_n113_));
  NA2        g103(.A(new_n30_), .B(new_n20_), .Y(new_n114_));
  NO2        g104(.A(new_n14_), .B(i6), .Y(new_n115_));
  AOI220     g105(.A0(new_n115_), .A1(i5), .B0(new_n114_), .B1(new_n82_), .Y(new_n116_));
  OAI220     g106(.A0(new_n116_), .A1(new_n19_), .B0(new_n102_), .B1(new_n35_), .Y(new_n117_));
  NO2        g107(.A(new_n117_), .B(new_n113_), .Y(new_n118_));
  AOI210     g108(.A0(new_n118_), .A1(new_n106_), .B0(i0), .Y(new_n119_));
  OAI210     g109(.A0(i6), .A1(new_n36_), .B0(new_n48_), .Y(new_n120_));
  NA2        g110(.A(new_n120_), .B(i2), .Y(new_n121_));
  NA2        g111(.A(new_n27_), .B(i8), .Y(new_n122_));
  AOI210     g112(.A0(new_n122_), .A1(new_n121_), .B0(i7), .Y(new_n123_));
  NA2        g113(.A(i8), .B(i7), .Y(new_n124_));
  OAI220     g114(.A0(new_n124_), .A1(i6), .B0(new_n46_), .B1(new_n36_), .Y(new_n125_));
  NA2        g115(.A(new_n125_), .B(new_n35_), .Y(new_n126_));
  NO2        g116(.A(new_n90_), .B(i5), .Y(new_n127_));
  NO2        g117(.A(new_n46_), .B(i3), .Y(new_n128_));
  OAI210     g118(.A0(new_n128_), .A1(new_n127_), .B0(i2), .Y(new_n129_));
  OAI210     g119(.A0(i6), .A1(new_n17_), .B0(new_n30_), .Y(new_n130_));
  NA2        g120(.A(new_n130_), .B(new_n71_), .Y(new_n131_));
  NA3        g121(.A(new_n131_), .B(new_n129_), .C(new_n126_), .Y(new_n132_));
  OAI210     g122(.A0(new_n132_), .A1(new_n123_), .B0(i0), .Y(new_n133_));
  NO2        g123(.A(new_n30_), .B(new_n35_), .Y(new_n134_));
  NO2        g124(.A(new_n81_), .B(new_n25_), .Y(new_n135_));
  OAI210     g125(.A0(i7), .A1(i3), .B0(i2), .Y(new_n136_));
  OAI220     g126(.A0(new_n136_), .A1(new_n135_), .B0(new_n124_), .B1(new_n38_), .Y(new_n137_));
  AOI220     g127(.A0(new_n137_), .A1(new_n26_), .B0(new_n115_), .B1(new_n134_), .Y(new_n138_));
  AOI210     g128(.A0(new_n138_), .A1(new_n133_), .B0(i1), .Y(new_n139_));
  OR4        g129(.A(new_n139_), .B(new_n119_), .C(new_n97_), .D(new_n55_), .Y(zori0));
endmodule


