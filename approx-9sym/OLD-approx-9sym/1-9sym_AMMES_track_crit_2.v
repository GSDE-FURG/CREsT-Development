// Benchmark "9sym_AMMES_track_crit_2" written by ABC on Sat Jul 29 04:07:46 2023

module \9sym_AMMES_track_crit_2  ( 
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
    new_n144_, new_n145_;
  INV        g000(.A(i4), .Y(new_n11_));
  INV        g001(.A(i1), .Y(new_n12_));
  NAi21      g002(.An(i6), .B(i7), .Y(new_n13_));
  NO2        g003(.A(new_n13_), .B(new_n12_), .Y(new_n14_));
  INV        g004(.A(i8), .Y(new_n15_));
  NAi21      g005(.An(i3), .B(i6), .Y(new_n16_));
  NO2        g006(.A(new_n16_), .B(new_n15_), .Y(new_n17_));
  OA210      g007(.A0(new_n17_), .A1(new_n14_), .B0(i5), .Y(new_n18_));
  INV        g008(.A(i3), .Y(new_n19_));
  INV        g009(.A(i0), .Y(new_n20_));
  NAi21      g010(.An(i5), .B(i6), .Y(new_n21_));
  OAI210     g011(.A0(i8), .A1(new_n20_), .B0(new_n21_), .Y(new_n22_));
  NA2        g012(.A(new_n22_), .B(i1), .Y(new_n23_));
  INV        g013(.A(i7), .Y(new_n24_));
  NO2        g014(.A(new_n24_), .B(i0), .Y(new_n25_));
  NA2        g015(.A(new_n25_), .B(i6), .Y(new_n26_));
  AOI210     g016(.A0(new_n26_), .A1(new_n23_), .B0(new_n19_), .Y(new_n27_));
  OAI210     g017(.A0(new_n27_), .A1(new_n18_), .B0(new_n11_), .Y(new_n28_));
  NAi21      g018(.An(i5), .B(i3), .Y(new_n29_));
  INV        g019(.A(i6), .Y(new_n30_));
  AOI220     g020(.A0(new_n15_), .A1(i7), .B0(new_n30_), .B1(i0), .Y(new_n31_));
  NAi21      g021(.An(i0), .B(i5), .Y(new_n32_));
  OAI220     g022(.A0(new_n32_), .A1(new_n13_), .B0(new_n31_), .B1(new_n29_), .Y(new_n33_));
  INV        g023(.A(i5), .Y(new_n34_));
  NO2        g024(.A(new_n34_), .B(new_n19_), .Y(new_n35_));
  OAI210     g025(.A0(i4), .A1(i3), .B0(new_n20_), .Y(new_n36_));
  NO2        g026(.A(new_n21_), .B(i3), .Y(new_n37_));
  NO2        g027(.A(new_n37_), .B(new_n15_), .Y(new_n38_));
  OAI210     g028(.A0(new_n36_), .A1(new_n35_), .B0(new_n38_), .Y(new_n39_));
  NAi21      g029(.An(i6), .B(i4), .Y(new_n40_));
  OAI210     g030(.A0(new_n24_), .A1(i3), .B0(new_n40_), .Y(new_n41_));
  NA2        g031(.A(new_n41_), .B(i0), .Y(new_n42_));
  AOI210     g032(.A0(new_n25_), .A1(i3), .B0(i8), .Y(new_n43_));
  AOI210     g033(.A0(new_n43_), .A1(new_n42_), .B0(new_n12_), .Y(new_n44_));
  AOI220     g034(.A0(new_n44_), .A1(new_n39_), .B0(new_n33_), .B1(i4), .Y(new_n45_));
  AOI210     g035(.A0(new_n45_), .A1(new_n28_), .B0(i2), .Y(new_n46_));
  INV        g036(.A(i2), .Y(new_n47_));
  NAi21      g037(.An(i6), .B(i8), .Y(new_n48_));
  NAi21      g038(.An(i5), .B(i4), .Y(new_n49_));
  NAi21      g039(.An(i8), .B(i6), .Y(new_n50_));
  OAI220     g040(.A0(new_n50_), .A1(new_n49_), .B0(new_n48_), .B1(new_n47_), .Y(new_n51_));
  NO2        g041(.A(new_n34_), .B(i4), .Y(new_n52_));
  NA2        g042(.A(i8), .B(i5), .Y(new_n53_));
  AOI210     g043(.A0(new_n24_), .A1(new_n34_), .B0(new_n11_), .Y(new_n54_));
  AOI220     g044(.A0(new_n54_), .A1(new_n53_), .B0(new_n52_), .B1(i2), .Y(new_n55_));
  NO2        g045(.A(i7), .B(i4), .Y(new_n56_));
  OAI220     g046(.A0(new_n56_), .A1(new_n47_), .B0(new_n15_), .B1(new_n20_), .Y(new_n57_));
  NO2        g047(.A(new_n30_), .B(i4), .Y(new_n58_));
  NAi21      g048(.An(i5), .B(i2), .Y(new_n59_));
  OAI210     g049(.A0(i8), .A1(new_n24_), .B0(new_n59_), .Y(new_n60_));
  AOI220     g050(.A0(new_n60_), .A1(new_n58_), .B0(new_n57_), .B1(new_n30_), .Y(new_n61_));
  OAI210     g051(.A0(new_n55_), .A1(i0), .B0(new_n61_), .Y(new_n62_));
  AOI220     g052(.A0(new_n62_), .A1(i1), .B0(new_n51_), .B1(i7), .Y(new_n63_));
  NAi21      g053(.An(i4), .B(i5), .Y(new_n64_));
  OAI220     g054(.A0(new_n64_), .A1(i8), .B0(new_n49_), .B1(i6), .Y(new_n65_));
  OAI210     g055(.A0(i6), .A1(new_n34_), .B0(new_n49_), .Y(new_n66_));
  NAi21      g056(.An(i4), .B(i8), .Y(new_n67_));
  NA2        g057(.A(new_n67_), .B(new_n50_), .Y(new_n68_));
  AOI220     g058(.A0(new_n68_), .A1(new_n66_), .B0(new_n65_), .B1(i3), .Y(new_n69_));
  NO3        g059(.A(new_n59_), .B(new_n30_), .C(i4), .Y(new_n70_));
  NAi21      g060(.An(i8), .B(i3), .Y(new_n71_));
  NO3        g061(.A(new_n71_), .B(i6), .C(new_n34_), .Y(new_n72_));
  OAI210     g062(.A0(new_n72_), .A1(new_n70_), .B0(i7), .Y(new_n73_));
  OAI210     g063(.A0(new_n69_), .A1(new_n12_), .B0(new_n73_), .Y(new_n74_));
  NO2        g064(.A(i6), .B(new_n34_), .Y(new_n75_));
  NO2        g065(.A(new_n71_), .B(new_n47_), .Y(new_n76_));
  OAI220     g066(.A0(new_n71_), .A1(new_n47_), .B0(new_n48_), .B1(new_n24_), .Y(new_n77_));
  NO2        g067(.A(i5), .B(new_n12_), .Y(new_n78_));
  AOI220     g068(.A0(new_n78_), .A1(new_n77_), .B0(new_n76_), .B1(new_n75_), .Y(new_n79_));
  OR2        g069(.A(new_n71_), .B(new_n40_), .Y(new_n80_));
  OAI220     g070(.A0(new_n80_), .A1(new_n59_), .B0(new_n79_), .B1(i4), .Y(new_n81_));
  AOI210     g071(.A0(new_n74_), .A1(new_n20_), .B0(new_n81_), .Y(new_n82_));
  OAI210     g072(.A0(new_n63_), .A1(i3), .B0(new_n82_), .Y(new_n83_));
  NAi21      g073(.An(i7), .B(i8), .Y(new_n84_));
  AO210      g074(.A0(new_n84_), .A1(new_n50_), .B0(i4), .Y(new_n85_));
  NO2        g075(.A(new_n56_), .B(i3), .Y(new_n86_));
  OAI210     g076(.A0(new_n24_), .A1(new_n11_), .B0(new_n86_), .Y(new_n87_));
  AOI210     g077(.A0(new_n87_), .A1(new_n85_), .B0(new_n34_), .Y(new_n88_));
  NAi21      g078(.An(i2), .B(i6), .Y(new_n89_));
  AOI210     g079(.A0(new_n89_), .A1(new_n13_), .B0(new_n11_), .Y(new_n90_));
  NA2        g080(.A(i7), .B(i6), .Y(new_n91_));
  NO2        g081(.A(new_n91_), .B(i5), .Y(new_n92_));
  OAI210     g082(.A0(new_n92_), .A1(new_n90_), .B0(new_n15_), .Y(new_n93_));
  NA2        g083(.A(new_n24_), .B(new_n30_), .Y(new_n94_));
  NA4        g084(.A(new_n94_), .B(new_n91_), .C(i8), .D(new_n47_), .Y(new_n95_));
  OAI220     g085(.A0(i8), .A1(i3), .B0(i7), .B1(i6), .Y(new_n96_));
  NA2        g086(.A(new_n96_), .B(i2), .Y(new_n97_));
  OAI210     g087(.A0(new_n24_), .A1(i4), .B0(new_n21_), .Y(new_n98_));
  NA3        g088(.A(new_n98_), .B(new_n91_), .C(i3), .Y(new_n99_));
  NA4        g089(.A(new_n99_), .B(new_n97_), .C(new_n95_), .D(new_n93_), .Y(new_n100_));
  OAI210     g090(.A0(new_n100_), .A1(new_n88_), .B0(i0), .Y(new_n101_));
  AOI210     g091(.A0(new_n32_), .A1(new_n16_), .B0(i2), .Y(new_n102_));
  NAi21      g092(.An(i3), .B(i2), .Y(new_n103_));
  AOI210     g093(.A0(new_n103_), .A1(new_n40_), .B0(i5), .Y(new_n104_));
  OAI210     g094(.A0(new_n104_), .A1(new_n102_), .B0(i8), .Y(new_n105_));
  NO3        g095(.A(new_n15_), .B(i5), .C(i2), .Y(new_n106_));
  NAi21      g096(.An(i0), .B(i2), .Y(new_n107_));
  NO2        g097(.A(new_n107_), .B(i4), .Y(new_n108_));
  OAI210     g098(.A0(new_n108_), .A1(new_n106_), .B0(i3), .Y(new_n109_));
  NO2        g099(.A(new_n30_), .B(i2), .Y(new_n110_));
  NO2        g100(.A(new_n47_), .B(i0), .Y(new_n111_));
  AOI220     g101(.A0(new_n111_), .A1(new_n66_), .B0(new_n110_), .B1(new_n52_), .Y(new_n112_));
  NA3        g102(.A(new_n112_), .B(new_n109_), .C(new_n105_), .Y(new_n113_));
  OAI220     g103(.A0(new_n84_), .A1(new_n19_), .B0(new_n16_), .B1(new_n34_), .Y(new_n114_));
  OAI220     g104(.A0(new_n71_), .A1(i7), .B0(new_n67_), .B1(i5), .Y(new_n115_));
  AOI220     g105(.A0(new_n115_), .A1(i6), .B0(new_n114_), .B1(new_n20_), .Y(new_n116_));
  NAi31      g106(.An(i8), .B(i6), .C(i4), .Y(new_n117_));
  OAI220     g107(.A0(new_n117_), .A1(new_n32_), .B0(new_n116_), .B1(new_n47_), .Y(new_n118_));
  AOI210     g108(.A0(new_n113_), .A1(i7), .B0(new_n118_), .Y(new_n119_));
  AOI210     g109(.A0(new_n119_), .A1(new_n101_), .B0(i1), .Y(new_n120_));
  OAI210     g110(.A0(new_n34_), .A1(i2), .B0(new_n67_), .Y(new_n121_));
  NA2        g111(.A(new_n121_), .B(i3), .Y(new_n122_));
  OR2        g112(.A(new_n49_), .B(new_n15_), .Y(new_n123_));
  AOI210     g113(.A0(new_n123_), .A1(new_n122_), .B0(i6), .Y(new_n124_));
  NO2        g114(.A(i8), .B(new_n12_), .Y(new_n125_));
  OAI210     g115(.A0(new_n110_), .A1(new_n52_), .B0(new_n125_), .Y(new_n126_));
  OAI210     g116(.A0(new_n103_), .A1(i4), .B0(new_n126_), .Y(new_n127_));
  OAI210     g117(.A0(new_n127_), .A1(new_n124_), .B0(i0), .Y(new_n128_));
  AOI210     g118(.A0(i5), .A1(i3), .B0(i2), .Y(new_n129_));
  OA210      g119(.A0(i5), .A1(i3), .B0(new_n129_), .Y(new_n130_));
  OAI210     g120(.A0(new_n130_), .A1(new_n37_), .B0(i4), .Y(new_n131_));
  NO3        g121(.A(new_n29_), .B(new_n30_), .C(i4), .Y(new_n132_));
  NA3        g122(.A(new_n64_), .B(new_n49_), .C(i2), .Y(new_n133_));
  NO2        g123(.A(new_n129_), .B(i0), .Y(new_n134_));
  AOI210     g124(.A0(new_n134_), .A1(new_n133_), .B0(new_n132_), .Y(new_n135_));
  AOI210     g125(.A0(new_n135_), .A1(new_n131_), .B0(new_n15_), .Y(new_n136_));
  NO2        g126(.A(new_n71_), .B(new_n40_), .Y(new_n137_));
  OAI220     g127(.A0(new_n103_), .A1(new_n11_), .B0(new_n50_), .B1(new_n19_), .Y(new_n138_));
  AOI210     g128(.A0(new_n138_), .A1(new_n20_), .B0(new_n137_), .Y(new_n139_));
  OAI220     g129(.A0(new_n107_), .A1(new_n48_), .B0(new_n89_), .B1(new_n64_), .Y(new_n140_));
  AOI210     g130(.A0(new_n103_), .A1(new_n29_), .B0(new_n117_), .Y(new_n141_));
  AOI210     g131(.A0(new_n140_), .A1(i1), .B0(new_n141_), .Y(new_n142_));
  OAI210     g132(.A0(new_n139_), .A1(new_n34_), .B0(new_n142_), .Y(new_n143_));
  NO2        g133(.A(new_n143_), .B(new_n136_), .Y(new_n144_));
  AOI210     g134(.A0(new_n144_), .A1(new_n128_), .B0(i7), .Y(new_n145_));
  OR4        g135(.A(new_n145_), .B(new_n120_), .C(new_n83_), .D(new_n46_), .Y(zori0));
endmodule


