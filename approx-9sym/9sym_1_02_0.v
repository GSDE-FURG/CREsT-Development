// Benchmark "9sym/9sym_1_2_0_esp" written by ABC on Wed Mar  8 02:20:56 2023

module \9sym/9sym_1_2_0_esp  ( 
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
  INV        g000(.A(i1), .Y(new_n11_));
  NOi21      g001(.An(i5), .B(i8), .Y(new_n12_));
  NOi21      g002(.An(i8), .B(i7), .Y(new_n13_));
  OA210      g003(.A0(new_n13_), .A1(new_n12_), .B0(new_n11_), .Y(new_n14_));
  NAi21      g004(.An(i6), .B(i1), .Y(new_n15_));
  NO2        g005(.A(new_n15_), .B(i8), .Y(new_n16_));
  OAI210     g006(.A0(new_n16_), .A1(new_n14_), .B0(i2), .Y(new_n17_));
  NO2        g007(.A(i7), .B(new_n11_), .Y(new_n18_));
  INV        g008(.A(i8), .Y(new_n19_));
  NO2        g009(.A(new_n19_), .B(i6), .Y(new_n20_));
  OAI210     g010(.A0(new_n20_), .A1(new_n12_), .B0(new_n18_), .Y(new_n21_));
  AOI210     g011(.A0(new_n21_), .A1(new_n17_), .B0(i3), .Y(new_n22_));
  INV        g012(.A(i3), .Y(new_n23_));
  INV        g013(.A(i2), .Y(new_n24_));
  NAi21      g014(.An(i6), .B(i5), .Y(new_n25_));
  NAi21      g015(.An(i4), .B(i1), .Y(new_n26_));
  OAI220     g016(.A0(new_n26_), .A1(i8), .B0(new_n25_), .B1(i7), .Y(new_n27_));
  NO2        g017(.A(i8), .B(i2), .Y(new_n28_));
  NA2        g018(.A(i8), .B(i6), .Y(new_n29_));
  NOi41      g019(.An(new_n29_), .B(new_n28_), .C(i7), .D(i4), .Y(new_n30_));
  AOI210     g020(.A0(new_n27_), .A1(new_n24_), .B0(new_n30_), .Y(new_n31_));
  INV        g021(.A(i4), .Y(new_n32_));
  NA4        g022(.A(new_n13_), .B(i5), .C(new_n32_), .D(new_n11_), .Y(new_n33_));
  OAI210     g023(.A0(new_n31_), .A1(new_n23_), .B0(new_n33_), .Y(new_n34_));
  OAI210     g024(.A0(new_n34_), .A1(new_n22_), .B0(i0), .Y(new_n35_));
  INV        g025(.A(i0), .Y(new_n36_));
  NAi21      g026(.An(i2), .B(i5), .Y(new_n37_));
  NAi21      g027(.An(i7), .B(i2), .Y(new_n38_));
  AOI210     g028(.A0(new_n38_), .A1(new_n37_), .B0(i1), .Y(new_n39_));
  NO3        g029(.A(i7), .B(i2), .C(new_n11_), .Y(new_n40_));
  OAI210     g030(.A0(new_n40_), .A1(new_n39_), .B0(i3), .Y(new_n41_));
  AO210      g031(.A0(new_n38_), .A1(new_n25_), .B0(new_n26_), .Y(new_n42_));
  AOI210     g032(.A0(new_n42_), .A1(new_n41_), .B0(new_n19_), .Y(new_n43_));
  INV        g033(.A(i5), .Y(new_n44_));
  NO2        g034(.A(i8), .B(new_n23_), .Y(new_n45_));
  NOi21      g035(.An(i2), .B(i3), .Y(new_n46_));
  NO2        g036(.A(new_n46_), .B(new_n45_), .Y(new_n47_));
  NO4        g037(.A(new_n47_), .B(new_n44_), .C(i4), .D(new_n11_), .Y(new_n48_));
  OAI210     g038(.A0(new_n48_), .A1(new_n43_), .B0(new_n36_), .Y(new_n49_));
  NO3        g039(.A(new_n44_), .B(i3), .C(i1), .Y(new_n50_));
  NA2        g040(.A(new_n50_), .B(new_n20_), .Y(new_n51_));
  OAI210     g041(.A0(i5), .A1(new_n11_), .B0(new_n25_), .Y(new_n52_));
  NA3        g042(.A(new_n52_), .B(new_n45_), .C(new_n32_), .Y(new_n53_));
  AO210      g043(.A0(new_n53_), .A1(new_n51_), .B0(new_n24_), .Y(new_n54_));
  NA3        g044(.A(new_n54_), .B(new_n49_), .C(new_n35_), .Y(new_n55_));
  NOi21      g045(.An(i2), .B(i7), .Y(new_n56_));
  NOi21      g046(.An(i7), .B(i8), .Y(new_n57_));
  OAI210     g047(.A0(new_n57_), .A1(new_n56_), .B0(new_n11_), .Y(new_n58_));
  NOi21      g048(.An(i3), .B(i2), .Y(new_n59_));
  OAI210     g049(.A0(new_n59_), .A1(new_n13_), .B0(new_n44_), .Y(new_n60_));
  AOI210     g050(.A0(new_n60_), .A1(new_n58_), .B0(new_n36_), .Y(new_n61_));
  NO2        g051(.A(new_n28_), .B(new_n36_), .Y(new_n62_));
  OAI210     g052(.A0(new_n46_), .A1(i0), .B0(i1), .Y(new_n63_));
  NO2        g053(.A(new_n63_), .B(new_n62_), .Y(new_n64_));
  NA3        g054(.A(i7), .B(i5), .C(new_n24_), .Y(new_n65_));
  NA2        g055(.A(new_n13_), .B(i2), .Y(new_n66_));
  AOI210     g056(.A0(new_n66_), .A1(new_n65_), .B0(i0), .Y(new_n67_));
  NA2        g057(.A(new_n19_), .B(i3), .Y(new_n68_));
  NOi21      g058(.An(i5), .B(i7), .Y(new_n69_));
  AOI210     g059(.A0(new_n44_), .A1(i2), .B0(new_n69_), .Y(new_n70_));
  NA2        g060(.A(i8), .B(new_n23_), .Y(new_n71_));
  NA2        g061(.A(i7), .B(new_n44_), .Y(new_n72_));
  OAI220     g062(.A0(new_n72_), .A1(new_n71_), .B0(new_n70_), .B1(new_n68_), .Y(new_n73_));
  NO4        g063(.A(new_n73_), .B(new_n67_), .C(new_n64_), .D(new_n61_), .Y(new_n74_));
  NO2        g064(.A(new_n74_), .B(i6), .Y(new_n75_));
  NA2        g065(.A(new_n69_), .B(new_n23_), .Y(new_n76_));
  AOI210     g066(.A0(new_n76_), .A1(new_n72_), .B0(new_n24_), .Y(new_n77_));
  NAi21      g067(.An(i5), .B(i3), .Y(new_n78_));
  AOI210     g068(.A0(i8), .A1(new_n24_), .B0(new_n12_), .Y(new_n79_));
  OAI210     g069(.A0(new_n79_), .A1(i3), .B0(new_n78_), .Y(new_n80_));
  AOI210     g070(.A0(new_n80_), .A1(i1), .B0(new_n77_), .Y(new_n81_));
  NOi21      g071(.An(i3), .B(i5), .Y(new_n82_));
  OAI210     g072(.A0(new_n57_), .A1(new_n13_), .B0(new_n82_), .Y(new_n83_));
  NOi31      g073(.An(i0), .B(i8), .C(i1), .Y(new_n84_));
  AOI210     g074(.A0(new_n13_), .A1(new_n23_), .B0(new_n84_), .Y(new_n85_));
  OAI210     g075(.A0(new_n85_), .A1(new_n44_), .B0(new_n83_), .Y(new_n86_));
  INV        g076(.A(i6), .Y(new_n87_));
  INV        g077(.A(i7), .Y(new_n88_));
  NO2        g078(.A(i5), .B(i3), .Y(new_n89_));
  NO3        g079(.A(i8), .B(i1), .C(i0), .Y(new_n90_));
  AOI210     g080(.A0(new_n89_), .A1(new_n88_), .B0(new_n90_), .Y(new_n91_));
  NA3        g081(.A(new_n19_), .B(new_n23_), .C(i1), .Y(new_n92_));
  OAI220     g082(.A0(new_n92_), .A1(new_n72_), .B0(new_n91_), .B1(new_n87_), .Y(new_n93_));
  AOI210     g083(.A0(new_n86_), .A1(new_n24_), .B0(new_n93_), .Y(new_n94_));
  OAI210     g084(.A0(new_n81_), .A1(i0), .B0(new_n94_), .Y(new_n95_));
  OA210      g085(.A0(new_n95_), .A1(new_n75_), .B0(i4), .Y(new_n96_));
  NA2        g086(.A(new_n88_), .B(i1), .Y(new_n97_));
  NO2        g087(.A(new_n97_), .B(i8), .Y(new_n98_));
  OA210      g088(.A0(new_n98_), .A1(new_n50_), .B0(new_n36_), .Y(new_n99_));
  NO2        g089(.A(new_n19_), .B(i5), .Y(new_n100_));
  AOI220     g090(.A0(new_n100_), .A1(new_n32_), .B0(new_n45_), .B1(new_n88_), .Y(new_n101_));
  NA3        g091(.A(new_n89_), .B(new_n32_), .C(i0), .Y(new_n102_));
  OAI210     g092(.A0(new_n101_), .A1(i1), .B0(new_n102_), .Y(new_n103_));
  OAI210     g093(.A0(new_n103_), .A1(new_n99_), .B0(i2), .Y(new_n104_));
  OAI210     g094(.A0(new_n19_), .A1(i2), .B0(new_n78_), .Y(new_n105_));
  NO3        g095(.A(i8), .B(i5), .C(new_n11_), .Y(new_n106_));
  AOI210     g096(.A0(new_n105_), .A1(new_n11_), .B0(new_n106_), .Y(new_n107_));
  NA2        g097(.A(new_n59_), .B(new_n12_), .Y(new_n108_));
  OAI210     g098(.A0(new_n107_), .A1(new_n36_), .B0(new_n108_), .Y(new_n109_));
  NO3        g099(.A(new_n19_), .B(i3), .C(i2), .Y(new_n110_));
  OAI210     g100(.A0(new_n110_), .A1(new_n84_), .B0(i5), .Y(new_n111_));
  AO210      g101(.A0(new_n71_), .A1(new_n37_), .B0(new_n97_), .Y(new_n112_));
  NO2        g102(.A(i2), .B(new_n11_), .Y(new_n113_));
  OAI210     g103(.A0(new_n113_), .A1(new_n13_), .B0(new_n82_), .Y(new_n114_));
  NA3        g104(.A(new_n114_), .B(new_n112_), .C(new_n111_), .Y(new_n115_));
  AOI220     g105(.A0(new_n115_), .A1(new_n32_), .B0(new_n109_), .B1(new_n88_), .Y(new_n116_));
  AOI210     g106(.A0(new_n116_), .A1(new_n104_), .B0(new_n87_), .Y(new_n117_));
  NO3        g107(.A(new_n19_), .B(i6), .C(i1), .Y(new_n118_));
  NOi21      g108(.An(new_n92_), .B(new_n118_), .Y(new_n119_));
  NO2        g109(.A(i6), .B(i2), .Y(new_n120_));
  NAi21      g110(.An(new_n120_), .B(new_n29_), .Y(new_n121_));
  NO2        g111(.A(new_n87_), .B(new_n23_), .Y(new_n122_));
  OR2        g112(.A(new_n89_), .B(i4), .Y(new_n123_));
  OA220      g113(.A0(new_n123_), .A1(new_n122_), .B0(new_n121_), .B1(i5), .Y(new_n124_));
  OAI220     g114(.A0(new_n124_), .A1(i1), .B0(new_n119_), .B1(i2), .Y(new_n125_));
  NAi21      g115(.An(i5), .B(i8), .Y(new_n126_));
  AOI210     g116(.A0(new_n126_), .A1(new_n37_), .B0(new_n15_), .Y(new_n127_));
  NO3        g117(.A(new_n37_), .B(new_n87_), .C(i1), .Y(new_n128_));
  OAI210     g118(.A0(new_n128_), .A1(new_n127_), .B0(new_n32_), .Y(new_n129_));
  NA3        g119(.A(new_n100_), .B(new_n59_), .C(new_n11_), .Y(new_n130_));
  NA2        g120(.A(new_n130_), .B(new_n129_), .Y(new_n131_));
  AOI210     g121(.A0(new_n125_), .A1(i0), .B0(new_n131_), .Y(new_n132_));
  OAI210     g122(.A0(i4), .A1(new_n23_), .B0(new_n25_), .Y(new_n133_));
  AOI220     g123(.A0(new_n133_), .A1(new_n11_), .B0(i6), .B1(new_n44_), .Y(new_n134_));
  OAI220     g124(.A0(i8), .A1(new_n11_), .B0(new_n87_), .B1(i4), .Y(new_n135_));
  NO2        g125(.A(new_n25_), .B(i8), .Y(new_n136_));
  AOI210     g126(.A0(new_n135_), .A1(new_n24_), .B0(new_n136_), .Y(new_n137_));
  OAI220     g127(.A0(new_n137_), .A1(new_n23_), .B0(new_n134_), .B1(new_n24_), .Y(new_n138_));
  NA2        g128(.A(i5), .B(i2), .Y(new_n139_));
  NO2        g129(.A(new_n120_), .B(i1), .Y(new_n140_));
  AOI220     g130(.A0(new_n140_), .A1(new_n139_), .B0(new_n52_), .B1(new_n24_), .Y(new_n141_));
  OAI220     g131(.A0(new_n141_), .A1(new_n19_), .B0(new_n121_), .B1(new_n26_), .Y(new_n142_));
  AOI220     g132(.A0(new_n142_), .A1(new_n23_), .B0(new_n138_), .B1(new_n36_), .Y(new_n143_));
  AOI210     g133(.A0(new_n143_), .A1(new_n132_), .B0(new_n88_), .Y(new_n144_));
  OR4        g134(.A(new_n144_), .B(new_n117_), .C(new_n96_), .D(new_n55_), .Y(zori0));
endmodule


