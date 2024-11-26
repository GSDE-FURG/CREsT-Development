// Benchmark "9sym_AMMES_track_crit_3" written by ABC on Sat Jul 29 04:32:53 2023

module \9sym_AMMES_track_crit_3  ( 
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
    new_n138_, new_n139_, new_n140_;
  INV        g000(.A(i7), .Y(new_n11_));
  INV        g001(.A(i0), .Y(new_n12_));
  INV        g002(.A(i8), .Y(new_n13_));
  NO2        g003(.A(new_n13_), .B(i3), .Y(new_n14_));
  INV        g004(.A(i4), .Y(new_n15_));
  NAi21      g005(.An(i8), .B(i3), .Y(new_n16_));
  OAI210     g006(.A0(new_n15_), .A1(i2), .B0(new_n16_), .Y(new_n17_));
  AOI210     g007(.A0(new_n17_), .A1(new_n12_), .B0(new_n14_), .Y(new_n18_));
  NO2        g008(.A(new_n18_), .B(new_n11_), .Y(new_n19_));
  INV        g009(.A(i2), .Y(new_n20_));
  NA2        g010(.A(i2), .B(i1), .Y(new_n21_));
  NO2        g011(.A(i8), .B(new_n15_), .Y(new_n22_));
  AOI220     g012(.A0(new_n22_), .A1(i3), .B0(new_n21_), .B1(i0), .Y(new_n23_));
  NO2        g013(.A(new_n11_), .B(i1), .Y(new_n24_));
  NO2        g014(.A(i8), .B(i4), .Y(new_n25_));
  AOI220     g015(.A0(new_n25_), .A1(i3), .B0(new_n24_), .B1(new_n12_), .Y(new_n26_));
  OAI220     g016(.A0(new_n26_), .A1(new_n20_), .B0(new_n23_), .B1(i7), .Y(new_n27_));
  OAI210     g017(.A0(new_n27_), .A1(new_n19_), .B0(i5), .Y(new_n28_));
  NAi21      g018(.An(i3), .B(i8), .Y(new_n29_));
  NAi21      g019(.An(i8), .B(i4), .Y(new_n30_));
  OAI210     g020(.A0(new_n30_), .A1(i2), .B0(new_n29_), .Y(new_n31_));
  NA2        g021(.A(new_n31_), .B(i0), .Y(new_n32_));
  INV        g022(.A(i5), .Y(new_n33_));
  NO2        g023(.A(new_n33_), .B(i4), .Y(new_n34_));
  NO2        g024(.A(i7), .B(new_n20_), .Y(new_n35_));
  NO2        g025(.A(new_n13_), .B(i0), .Y(new_n36_));
  OAI210     g026(.A0(new_n35_), .A1(new_n34_), .B0(new_n36_), .Y(new_n37_));
  NO2        g027(.A(new_n11_), .B(i2), .Y(new_n38_));
  NA2        g028(.A(new_n38_), .B(new_n34_), .Y(new_n39_));
  INV        g029(.A(i3), .Y(new_n40_));
  NA2        g030(.A(new_n11_), .B(new_n15_), .Y(new_n41_));
  NA3        g031(.A(new_n41_), .B(new_n40_), .C(i2), .Y(new_n42_));
  NA4        g032(.A(new_n42_), .B(new_n39_), .C(new_n37_), .D(new_n32_), .Y(new_n43_));
  NAi21      g033(.An(i4), .B(i3), .Y(new_n44_));
  NO2        g034(.A(new_n13_), .B(i2), .Y(new_n45_));
  OAI210     g035(.A0(new_n45_), .A1(new_n22_), .B0(new_n24_), .Y(new_n46_));
  NA2        g036(.A(new_n46_), .B(new_n44_), .Y(new_n47_));
  NAi21      g037(.An(i1), .B(i7), .Y(new_n48_));
  NAi21      g038(.An(i7), .B(i8), .Y(new_n49_));
  AOI210     g039(.A0(new_n49_), .A1(new_n48_), .B0(new_n12_), .Y(new_n50_));
  AOI220     g040(.A0(new_n50_), .A1(new_n47_), .B0(new_n43_), .B1(i1), .Y(new_n51_));
  AOI210     g041(.A0(new_n51_), .A1(new_n28_), .B0(i6), .Y(new_n52_));
  NA2        g042(.A(i8), .B(i1), .Y(new_n53_));
  NA3        g043(.A(new_n53_), .B(new_n15_), .C(i0), .Y(new_n54_));
  NO2        g044(.A(i4), .B(i2), .Y(new_n55_));
  NA2        g045(.A(new_n21_), .B(new_n14_), .Y(new_n56_));
  OAI210     g046(.A0(new_n56_), .A1(new_n55_), .B0(new_n54_), .Y(new_n57_));
  NAi21      g047(.An(i1), .B(i8), .Y(new_n58_));
  NAi21      g048(.An(i8), .B(i1), .Y(new_n59_));
  AOI210     g049(.A0(new_n59_), .A1(new_n58_), .B0(new_n12_), .Y(new_n60_));
  NAi21      g050(.An(i4), .B(i1), .Y(new_n61_));
  AOI210     g051(.A0(new_n61_), .A1(new_n16_), .B0(new_n33_), .Y(new_n62_));
  OAI210     g052(.A0(new_n62_), .A1(new_n60_), .B0(new_n20_), .Y(new_n63_));
  NO2        g053(.A(i4), .B(i3), .Y(new_n64_));
  NAi21      g054(.An(new_n53_), .B(new_n64_), .Y(new_n65_));
  NA2        g055(.A(i3), .B(i1), .Y(new_n66_));
  NAi41      g056(.An(new_n64_), .B(new_n66_), .C(new_n13_), .D(i2), .Y(new_n67_));
  NA3        g057(.A(new_n67_), .B(new_n65_), .C(new_n63_), .Y(new_n68_));
  AOI220     g058(.A0(new_n68_), .A1(i6), .B0(new_n57_), .B1(i5), .Y(new_n69_));
  AOI210     g059(.A0(new_n34_), .A1(new_n14_), .B0(new_n24_), .Y(new_n70_));
  OAI210     g060(.A0(new_n34_), .A1(new_n14_), .B0(i6), .Y(new_n71_));
  INV        g061(.A(i1), .Y(new_n72_));
  NAi21      g062(.An(i3), .B(i7), .Y(new_n73_));
  AOI210     g063(.A0(new_n73_), .A1(new_n44_), .B0(new_n72_), .Y(new_n74_));
  INV        g064(.A(i6), .Y(new_n75_));
  NO2        g065(.A(new_n75_), .B(i1), .Y(new_n76_));
  AOI210     g066(.A0(new_n76_), .A1(i4), .B0(new_n74_), .Y(new_n77_));
  NA2        g067(.A(new_n13_), .B(i0), .Y(new_n78_));
  OAI220     g068(.A0(new_n78_), .A1(new_n77_), .B0(new_n71_), .B1(new_n70_), .Y(new_n79_));
  AOI210     g069(.A0(i8), .A1(i4), .B0(i3), .Y(new_n80_));
  NO2        g070(.A(new_n80_), .B(new_n25_), .Y(new_n81_));
  NA3        g071(.A(i5), .B(new_n72_), .C(i0), .Y(new_n82_));
  NO2        g072(.A(i8), .B(new_n72_), .Y(new_n83_));
  NO3        g073(.A(new_n11_), .B(new_n75_), .C(i4), .Y(new_n84_));
  NA3        g074(.A(new_n84_), .B(new_n83_), .C(new_n40_), .Y(new_n85_));
  OAI210     g075(.A0(new_n82_), .A1(new_n81_), .B0(new_n85_), .Y(new_n86_));
  AOI210     g076(.A0(new_n79_), .A1(new_n20_), .B0(new_n86_), .Y(new_n87_));
  OAI210     g077(.A0(new_n69_), .A1(i7), .B0(new_n87_), .Y(new_n88_));
  NO2        g078(.A(i8), .B(new_n40_), .Y(new_n89_));
  OAI220     g079(.A0(new_n13_), .A1(i2), .B0(i3), .B1(new_n72_), .Y(new_n90_));
  AOI220     g080(.A0(new_n90_), .A1(new_n12_), .B0(new_n89_), .B1(new_n20_), .Y(new_n91_));
  NAi21      g081(.An(i2), .B(i3), .Y(new_n92_));
  NO2        g082(.A(new_n92_), .B(new_n49_), .Y(new_n93_));
  NA3        g083(.A(new_n92_), .B(new_n49_), .C(i0), .Y(new_n94_));
  AOI210     g084(.A0(new_n66_), .A1(new_n12_), .B0(i6), .Y(new_n95_));
  AOI210     g085(.A0(new_n95_), .A1(new_n94_), .B0(new_n93_), .Y(new_n96_));
  OAI210     g086(.A0(new_n91_), .A1(new_n11_), .B0(new_n96_), .Y(new_n97_));
  AN2        g087(.A(i8), .B(i7), .Y(new_n98_));
  OAI220     g088(.A0(new_n92_), .A1(i1), .B0(new_n61_), .B1(i6), .Y(new_n99_));
  AOI220     g089(.A0(new_n99_), .A1(new_n98_), .B0(new_n97_), .B1(i4), .Y(new_n100_));
  AOI210     g090(.A0(new_n44_), .A1(new_n29_), .B0(i2), .Y(new_n101_));
  AOI210     g091(.A0(new_n22_), .A1(new_n12_), .B0(new_n101_), .Y(new_n102_));
  AN3        g092(.A(i8), .B(i4), .C(i3), .Y(new_n103_));
  NO4        g093(.A(new_n103_), .B(new_n80_), .C(new_n25_), .D(i7), .Y(new_n104_));
  NO2        g094(.A(new_n73_), .B(new_n30_), .Y(new_n105_));
  NO2        g095(.A(i7), .B(i3), .Y(new_n106_));
  NO4        g096(.A(new_n106_), .B(new_n98_), .C(i1), .D(new_n12_), .Y(new_n107_));
  NO3        g097(.A(new_n107_), .B(new_n105_), .C(new_n104_), .Y(new_n108_));
  OAI210     g098(.A0(new_n102_), .A1(new_n72_), .B0(new_n108_), .Y(new_n109_));
  AOI210     g099(.A0(new_n49_), .A1(new_n48_), .B0(new_n15_), .Y(new_n110_));
  OAI210     g100(.A0(new_n110_), .A1(new_n84_), .B0(new_n12_), .Y(new_n111_));
  NO2        g101(.A(i6), .B(i1), .Y(new_n112_));
  OAI210     g102(.A0(new_n112_), .A1(new_n64_), .B0(i0), .Y(new_n113_));
  NO2        g103(.A(new_n13_), .B(i1), .Y(new_n114_));
  OAI210     g104(.A0(new_n75_), .A1(i4), .B0(new_n73_), .Y(new_n115_));
  OAI210     g105(.A0(i6), .A1(new_n15_), .B0(new_n61_), .Y(new_n116_));
  AOI220     g106(.A0(new_n116_), .A1(new_n89_), .B0(new_n115_), .B1(new_n114_), .Y(new_n117_));
  NA3        g107(.A(new_n117_), .B(new_n113_), .C(new_n111_), .Y(new_n118_));
  AOI220     g108(.A0(new_n118_), .A1(i2), .B0(new_n109_), .B1(i6), .Y(new_n119_));
  AOI210     g109(.A0(new_n119_), .A1(new_n100_), .B0(i5), .Y(new_n120_));
  OAI210     g110(.A0(new_n83_), .A1(new_n35_), .B0(i4), .Y(new_n121_));
  NA2        g111(.A(i6), .B(new_n72_), .Y(new_n122_));
  AO210      g112(.A0(new_n122_), .A1(new_n61_), .B0(new_n20_), .Y(new_n123_));
  AOI210     g113(.A0(new_n123_), .A1(new_n121_), .B0(i3), .Y(new_n124_));
  NO2        g114(.A(new_n122_), .B(new_n30_), .Y(new_n125_));
  OAI210     g115(.A0(new_n125_), .A1(new_n124_), .B0(i5), .Y(new_n126_));
  OAI210     g116(.A0(new_n11_), .A1(i4), .B0(new_n49_), .Y(new_n127_));
  AOI220     g117(.A0(new_n127_), .A1(i2), .B0(new_n45_), .B1(i5), .Y(new_n128_));
  AOI220     g118(.A0(new_n83_), .A1(i5), .B0(new_n38_), .B1(i6), .Y(new_n129_));
  OAI220     g119(.A0(new_n129_), .A1(i4), .B0(new_n128_), .B1(i1), .Y(new_n130_));
  NO2        g120(.A(i8), .B(i7), .Y(new_n131_));
  NO3        g121(.A(new_n131_), .B(new_n98_), .C(new_n92_), .Y(new_n132_));
  NO3        g122(.A(new_n29_), .B(new_n15_), .C(i2), .Y(new_n133_));
  AN2        g123(.A(i8), .B(i4), .Y(new_n134_));
  NO2        g124(.A(i8), .B(i6), .Y(new_n135_));
  NO4        g125(.A(new_n135_), .B(new_n134_), .C(i7), .D(new_n20_), .Y(new_n136_));
  NO3        g126(.A(new_n136_), .B(new_n133_), .C(new_n132_), .Y(new_n137_));
  NO2        g127(.A(new_n137_), .B(new_n72_), .Y(new_n138_));
  AOI210     g128(.A0(new_n130_), .A1(i3), .B0(new_n138_), .Y(new_n139_));
  AOI210     g129(.A0(new_n139_), .A1(new_n126_), .B0(i0), .Y(new_n140_));
  OR4        g130(.A(new_n140_), .B(new_n120_), .C(new_n88_), .D(new_n52_), .Y(zori0));
endmodule


