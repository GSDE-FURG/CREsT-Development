// Benchmark "9sym/9sym_1_7_0_esp" written by ABC on Wed Mar  8 02:33:56 2023

module \9sym/9sym_1_7_0_esp  ( 
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
    new_n132_, new_n133_;
  NOi21      g000(.An(i6), .B(i7), .Y(new_n11_));
  NA2        g001(.A(new_n11_), .B(i2), .Y(new_n12_));
  INV        g002(.A(i2), .Y(new_n13_));
  NA3        g003(.A(i7), .B(i3), .C(new_n13_), .Y(new_n14_));
  AOI210     g004(.A0(new_n14_), .A1(new_n12_), .B0(i8), .Y(new_n15_));
  NA2        g005(.A(i7), .B(i3), .Y(new_n16_));
  NA2        g006(.A(i8), .B(new_n13_), .Y(new_n17_));
  NO2        g007(.A(i4), .B(i3), .Y(new_n18_));
  NOi31      g008(.An(new_n16_), .B(new_n18_), .C(new_n17_), .Y(new_n19_));
  NOi21      g009(.An(i3), .B(i5), .Y(new_n20_));
  NA2        g010(.A(new_n20_), .B(i4), .Y(new_n21_));
  NOi21      g011(.An(i8), .B(i7), .Y(new_n22_));
  NO2        g012(.A(new_n22_), .B(i4), .Y(new_n23_));
  NA2        g013(.A(i6), .B(i4), .Y(new_n24_));
  NA2        g014(.A(new_n24_), .B(i2), .Y(new_n25_));
  OAI210     g015(.A0(new_n25_), .A1(new_n23_), .B0(new_n21_), .Y(new_n26_));
  NO3        g016(.A(new_n26_), .B(new_n19_), .C(new_n15_), .Y(new_n27_));
  INV        g017(.A(i5), .Y(new_n28_));
  INV        g018(.A(i6), .Y(new_n29_));
  NA2        g019(.A(i7), .B(new_n29_), .Y(new_n30_));
  INV        g020(.A(i4), .Y(new_n31_));
  NA2        g021(.A(i8), .B(new_n31_), .Y(new_n32_));
  NOi21      g022(.An(i3), .B(i4), .Y(new_n33_));
  NOi21      g023(.An(i8), .B(i3), .Y(new_n34_));
  NO2        g024(.A(new_n34_), .B(new_n33_), .Y(new_n35_));
  NA2        g025(.A(i6), .B(new_n13_), .Y(new_n36_));
  OAI220     g026(.A0(new_n36_), .A1(new_n35_), .B0(new_n32_), .B1(new_n30_), .Y(new_n37_));
  NOi21      g027(.An(i7), .B(i6), .Y(new_n38_));
  NOi21      g028(.An(i3), .B(i8), .Y(new_n39_));
  AOI220     g029(.A0(new_n39_), .A1(new_n28_), .B0(new_n38_), .B1(new_n18_), .Y(new_n40_));
  NA2        g030(.A(i7), .B(i6), .Y(new_n41_));
  INV        g031(.A(i8), .Y(new_n42_));
  NA2        g032(.A(new_n18_), .B(new_n42_), .Y(new_n43_));
  OAI220     g033(.A0(new_n43_), .A1(new_n41_), .B0(new_n40_), .B1(new_n13_), .Y(new_n44_));
  AOI210     g034(.A0(new_n37_), .A1(new_n28_), .B0(new_n44_), .Y(new_n45_));
  OAI210     g035(.A0(new_n27_), .A1(i0), .B0(new_n45_), .Y(new_n46_));
  NA2        g036(.A(new_n46_), .B(i1), .Y(new_n47_));
  INV        g037(.A(i1), .Y(new_n48_));
  INV        g038(.A(i3), .Y(new_n49_));
  AOI210     g039(.A0(i6), .A1(new_n49_), .B0(new_n20_), .Y(new_n50_));
  NA2        g040(.A(new_n49_), .B(i2), .Y(new_n51_));
  OAI220     g041(.A0(new_n51_), .A1(i5), .B0(new_n50_), .B1(i2), .Y(new_n52_));
  INV        g042(.A(i7), .Y(new_n53_));
  NA2        g043(.A(new_n53_), .B(i3), .Y(new_n54_));
  NA2        g044(.A(i6), .B(new_n31_), .Y(new_n55_));
  OAI210     g045(.A0(new_n55_), .A1(i5), .B0(new_n54_), .Y(new_n56_));
  AOI220     g046(.A0(new_n56_), .A1(i2), .B0(new_n52_), .B1(i7), .Y(new_n57_));
  INV        g047(.A(i0), .Y(new_n58_));
  NO2        g048(.A(new_n12_), .B(new_n49_), .Y(new_n59_));
  NA2        g049(.A(new_n31_), .B(i2), .Y(new_n60_));
  NA2        g050(.A(new_n42_), .B(i4), .Y(new_n61_));
  OAI220     g051(.A0(new_n61_), .A1(new_n29_), .B0(new_n60_), .B1(new_n16_), .Y(new_n62_));
  AOI210     g052(.A0(new_n62_), .A1(new_n58_), .B0(new_n59_), .Y(new_n63_));
  OAI210     g053(.A0(new_n57_), .A1(new_n42_), .B0(new_n63_), .Y(new_n64_));
  NA3        g054(.A(new_n22_), .B(new_n28_), .C(i4), .Y(new_n65_));
  NA2        g055(.A(new_n31_), .B(new_n58_), .Y(new_n66_));
  OAI210     g056(.A0(new_n66_), .A1(new_n41_), .B0(new_n65_), .Y(new_n67_));
  NA2        g057(.A(i8), .B(new_n53_), .Y(new_n68_));
  OAI220     g058(.A0(new_n61_), .A1(new_n13_), .B0(new_n55_), .B1(new_n68_), .Y(new_n69_));
  AOI220     g059(.A0(new_n69_), .A1(new_n28_), .B0(new_n67_), .B1(new_n13_), .Y(new_n70_));
  OAI220     g060(.A0(i8), .A1(i1), .B0(i5), .B1(i3), .Y(new_n71_));
  OAI220     g061(.A0(new_n55_), .A1(new_n58_), .B0(new_n30_), .B1(new_n31_), .Y(new_n72_));
  OAI210     g062(.A0(i7), .A1(i3), .B0(i8), .Y(new_n73_));
  NAi21      g063(.An(new_n24_), .B(new_n73_), .Y(new_n74_));
  AOI220     g064(.A0(i8), .A1(i4), .B0(i7), .B1(i6), .Y(new_n75_));
  NA2        g065(.A(i2), .B(new_n58_), .Y(new_n76_));
  OAI210     g066(.A0(new_n76_), .A1(new_n75_), .B0(new_n74_), .Y(new_n77_));
  AOI220     g067(.A0(new_n77_), .A1(new_n28_), .B0(new_n72_), .B1(new_n71_), .Y(new_n78_));
  OAI210     g068(.A0(new_n70_), .A1(new_n49_), .B0(new_n78_), .Y(new_n79_));
  AOI210     g069(.A0(new_n64_), .A1(new_n48_), .B0(new_n79_), .Y(new_n80_));
  NA2        g070(.A(new_n53_), .B(i4), .Y(new_n81_));
  NA2        g071(.A(i6), .B(new_n48_), .Y(new_n82_));
  AOI210     g072(.A0(new_n82_), .A1(new_n81_), .B0(i3), .Y(new_n83_));
  OAI210     g073(.A0(new_n83_), .A1(new_n38_), .B0(i2), .Y(new_n84_));
  OAI210     g074(.A0(new_n17_), .A1(i1), .B0(new_n30_), .Y(new_n85_));
  NOi21      g075(.An(i2), .B(i4), .Y(new_n86_));
  NOi21      g076(.An(i4), .B(i8), .Y(new_n87_));
  OAI210     g077(.A0(new_n87_), .A1(new_n86_), .B0(new_n49_), .Y(new_n88_));
  NOi31      g078(.An(i3), .B(i8), .C(i4), .Y(new_n89_));
  AOI210     g079(.A0(i8), .A1(new_n29_), .B0(new_n89_), .Y(new_n90_));
  AOI210     g080(.A0(new_n90_), .A1(new_n88_), .B0(new_n48_), .Y(new_n91_));
  AOI210     g081(.A0(new_n85_), .A1(i3), .B0(new_n91_), .Y(new_n92_));
  AOI210     g082(.A0(new_n92_), .A1(new_n84_), .B0(i0), .Y(new_n93_));
  OAI210     g083(.A0(new_n38_), .A1(new_n11_), .B0(i1), .Y(new_n94_));
  NOi21      g084(.An(i7), .B(i1), .Y(new_n95_));
  OAI210     g085(.A0(new_n95_), .A1(new_n34_), .B0(i6), .Y(new_n96_));
  AOI210     g086(.A0(new_n96_), .A1(new_n94_), .B0(i4), .Y(new_n97_));
  NA2        g087(.A(i8), .B(new_n49_), .Y(new_n98_));
  AOI210     g088(.A0(new_n53_), .A1(i4), .B0(new_n38_), .Y(new_n99_));
  NA2        g089(.A(new_n39_), .B(new_n11_), .Y(new_n100_));
  OAI210     g090(.A0(new_n99_), .A1(new_n98_), .B0(new_n100_), .Y(new_n101_));
  OAI210     g091(.A0(new_n101_), .A1(new_n97_), .B0(new_n13_), .Y(new_n102_));
  AOI210     g092(.A0(new_n34_), .A1(new_n48_), .B0(new_n89_), .Y(new_n103_));
  OA220      g093(.A0(new_n103_), .A1(new_n13_), .B0(new_n61_), .B1(new_n54_), .Y(new_n104_));
  OAI210     g094(.A0(new_n104_), .A1(i6), .B0(new_n102_), .Y(new_n105_));
  OAI210     g095(.A0(new_n105_), .A1(new_n93_), .B0(i5), .Y(new_n106_));
  NA2        g096(.A(new_n28_), .B(i4), .Y(new_n107_));
  NA2        g097(.A(new_n53_), .B(i5), .Y(new_n108_));
  AOI210     g098(.A0(new_n108_), .A1(new_n107_), .B0(i2), .Y(new_n109_));
  NO2        g099(.A(new_n68_), .B(i4), .Y(new_n110_));
  OAI210     g100(.A0(new_n110_), .A1(new_n109_), .B0(i3), .Y(new_n111_));
  OAI210     g101(.A0(new_n31_), .A1(i2), .B0(new_n42_), .Y(new_n112_));
  NA3        g102(.A(new_n112_), .B(new_n73_), .C(i1), .Y(new_n113_));
  NO3        g103(.A(new_n53_), .B(i5), .C(i1), .Y(new_n114_));
  NO3        g104(.A(i8), .B(i3), .C(new_n48_), .Y(new_n115_));
  OAI210     g105(.A0(new_n115_), .A1(new_n114_), .B0(i2), .Y(new_n116_));
  NO2        g106(.A(new_n42_), .B(i2), .Y(new_n117_));
  OAI210     g107(.A0(new_n33_), .A1(new_n117_), .B0(new_n95_), .Y(new_n118_));
  AN4        g108(.A(new_n118_), .B(new_n116_), .C(new_n113_), .D(new_n65_), .Y(new_n119_));
  AOI210     g109(.A0(new_n119_), .A1(new_n111_), .B0(i6), .Y(new_n120_));
  NA2        g110(.A(i4), .B(new_n13_), .Y(new_n121_));
  NA3        g111(.A(new_n121_), .B(new_n51_), .C(new_n48_), .Y(new_n122_));
  OAI210     g112(.A0(i7), .A1(i3), .B0(i1), .Y(new_n123_));
  NOi21      g113(.An(new_n123_), .B(new_n28_), .Y(new_n124_));
  OAI210     g114(.A0(new_n31_), .A1(new_n49_), .B0(new_n13_), .Y(new_n125_));
  OAI220     g115(.A0(new_n125_), .A1(new_n123_), .B0(new_n60_), .B1(new_n54_), .Y(new_n126_));
  AOI210     g116(.A0(new_n124_), .A1(new_n122_), .B0(new_n126_), .Y(new_n127_));
  AOI220     g117(.A0(i8), .A1(i5), .B0(i4), .B1(i2), .Y(new_n128_));
  OAI220     g118(.A0(new_n128_), .A1(i1), .B0(new_n51_), .B1(new_n32_), .Y(new_n129_));
  AN3        g119(.A(new_n95_), .B(new_n18_), .C(i5), .Y(new_n130_));
  AOI210     g120(.A0(new_n129_), .A1(new_n53_), .B0(new_n130_), .Y(new_n131_));
  OAI210     g121(.A0(new_n127_), .A1(i8), .B0(new_n131_), .Y(new_n132_));
  OAI210     g122(.A0(new_n132_), .A1(new_n120_), .B0(i0), .Y(new_n133_));
  NA4        g123(.A(new_n133_), .B(new_n106_), .C(new_n80_), .D(new_n47_), .Y(zori0));
endmodule


