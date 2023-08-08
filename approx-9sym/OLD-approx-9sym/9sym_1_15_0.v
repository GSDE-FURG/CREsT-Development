// Benchmark "9sym/9sym_1_15_0_esp" written by ABC on Wed Mar  8 02:19:35 2023

module \9sym/9sym_1_15_0_esp  ( 
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
    new_n102_, new_n103_, new_n104_, new_n105_;
  INV        g00(.A(i1), .Y(new_n11_));
  INV        g01(.A(i7), .Y(new_n12_));
  NAi21      g02(.An(i4), .B(i3), .Y(new_n13_));
  NO2        g03(.A(new_n13_), .B(i0), .Y(new_n14_));
  INV        g04(.A(i8), .Y(new_n15_));
  NO3        g05(.A(new_n15_), .B(i5), .C(i3), .Y(new_n16_));
  OAI210     g06(.A0(new_n16_), .A1(new_n14_), .B0(i2), .Y(new_n17_));
  NA2        g07(.A(i5), .B(i3), .Y(new_n18_));
  INV        g08(.A(i5), .Y(new_n19_));
  NAi21      g09(.An(i2), .B(i8), .Y(new_n20_));
  NO2        g10(.A(i6), .B(i3), .Y(new_n21_));
  OAI220     g11(.A0(new_n21_), .A1(new_n20_), .B0(new_n19_), .B1(i4), .Y(new_n22_));
  NAi21      g12(.An(i6), .B(i0), .Y(new_n23_));
  AOI210     g13(.A0(new_n20_), .A1(new_n13_), .B0(new_n23_), .Y(new_n24_));
  AOI210     g14(.A0(new_n22_), .A1(new_n18_), .B0(new_n24_), .Y(new_n25_));
  AOI210     g15(.A0(new_n25_), .A1(new_n17_), .B0(new_n12_), .Y(new_n26_));
  INV        g16(.A(i0), .Y(new_n27_));
  INV        g17(.A(i2), .Y(new_n28_));
  INV        g18(.A(i3), .Y(new_n29_));
  NAi21      g19(.An(i3), .B(i6), .Y(new_n30_));
  OAI220     g20(.A0(new_n30_), .A1(new_n28_), .B0(new_n20_), .B1(new_n29_), .Y(new_n31_));
  NO4        g21(.A(new_n15_), .B(i6), .C(i3), .D(new_n28_), .Y(new_n32_));
  AOI210     g22(.A0(new_n31_), .A1(new_n27_), .B0(new_n32_), .Y(new_n33_));
  NAi31      g23(.An(i4), .B(i8), .C(i6), .Y(new_n34_));
  NAi21      g24(.An(i5), .B(i2), .Y(new_n35_));
  AOI210     g25(.A0(new_n34_), .A1(new_n23_), .B0(new_n35_), .Y(new_n36_));
  NA3        g26(.A(i8), .B(i5), .C(i0), .Y(new_n37_));
  NA3        g27(.A(i6), .B(i3), .C(i2), .Y(new_n38_));
  AOI210     g28(.A0(new_n38_), .A1(new_n37_), .B0(i7), .Y(new_n39_));
  NA2        g29(.A(i5), .B(i0), .Y(new_n40_));
  NAi21      g30(.An(i8), .B(i4), .Y(new_n41_));
  AOI210     g31(.A0(new_n40_), .A1(new_n12_), .B0(new_n41_), .Y(new_n42_));
  NO3        g32(.A(new_n42_), .B(new_n39_), .C(new_n36_), .Y(new_n43_));
  OAI210     g33(.A0(new_n33_), .A1(new_n19_), .B0(new_n43_), .Y(new_n44_));
  OAI210     g34(.A0(new_n44_), .A1(new_n26_), .B0(new_n11_), .Y(new_n45_));
  AN2        g35(.A(i8), .B(i6), .Y(new_n46_));
  OA210      g36(.A0(new_n46_), .A1(i3), .B0(new_n27_), .Y(new_n47_));
  NAi21      g37(.An(i6), .B(i8), .Y(new_n48_));
  NAi21      g38(.An(i4), .B(i7), .Y(new_n49_));
  OAI220     g39(.A0(new_n49_), .A1(new_n48_), .B0(i8), .B1(new_n29_), .Y(new_n50_));
  OAI210     g40(.A0(new_n50_), .A1(new_n47_), .B0(i1), .Y(new_n51_));
  NAi21      g41(.An(i7), .B(i8), .Y(new_n52_));
  OAI210     g42(.A0(new_n52_), .A1(new_n13_), .B0(new_n41_), .Y(new_n53_));
  NA2        g43(.A(new_n53_), .B(i6), .Y(new_n54_));
  NAi21      g44(.An(i6), .B(i4), .Y(new_n55_));
  NO3        g45(.A(new_n55_), .B(new_n12_), .C(i3), .Y(new_n56_));
  OAI220     g46(.A0(new_n55_), .A1(new_n52_), .B0(new_n30_), .B1(i4), .Y(new_n57_));
  AOI210     g47(.A0(new_n57_), .A1(i0), .B0(new_n56_), .Y(new_n58_));
  NA3        g48(.A(new_n58_), .B(new_n54_), .C(new_n51_), .Y(new_n59_));
  NAi21      g49(.An(i8), .B(i5), .Y(new_n60_));
  NAi21      g50(.An(i0), .B(i4), .Y(new_n61_));
  NO2        g51(.A(i8), .B(i5), .Y(new_n62_));
  NAi21      g52(.An(i7), .B(i0), .Y(new_n63_));
  OAI220     g53(.A0(new_n63_), .A1(new_n62_), .B0(new_n61_), .B1(new_n60_), .Y(new_n64_));
  NO3        g54(.A(new_n48_), .B(new_n19_), .C(i0), .Y(new_n65_));
  AOI210     g55(.A0(new_n64_), .A1(new_n29_), .B0(new_n65_), .Y(new_n66_));
  NAi21      g56(.An(i6), .B(i3), .Y(new_n67_));
  OAI210     g57(.A0(new_n67_), .A1(new_n60_), .B0(new_n30_), .Y(new_n68_));
  INV        g58(.A(i4), .Y(new_n69_));
  NO2        g59(.A(i7), .B(new_n69_), .Y(new_n70_));
  INV        g60(.A(i6), .Y(new_n71_));
  NA2        g61(.A(new_n52_), .B(new_n71_), .Y(new_n72_));
  NO4        g62(.A(new_n46_), .B(new_n21_), .C(i4), .D(new_n27_), .Y(new_n73_));
  AOI220     g63(.A0(new_n73_), .A1(new_n72_), .B0(new_n70_), .B1(new_n68_), .Y(new_n74_));
  OAI210     g64(.A0(new_n66_), .A1(new_n11_), .B0(new_n74_), .Y(new_n75_));
  AOI210     g65(.A0(new_n59_), .A1(new_n19_), .B0(new_n75_), .Y(new_n76_));
  NAi21      g66(.An(i3), .B(i5), .Y(new_n77_));
  AOI210     g67(.A0(new_n77_), .A1(new_n52_), .B0(i4), .Y(new_n78_));
  NO3        g68(.A(i8), .B(i7), .C(new_n71_), .Y(new_n79_));
  OAI210     g69(.A0(new_n79_), .A1(new_n78_), .B0(i1), .Y(new_n80_));
  NAi31      g70(.An(i5), .B(i7), .C(i6), .Y(new_n81_));
  AN2        g71(.A(new_n81_), .B(new_n55_), .Y(new_n82_));
  AOI210     g72(.A0(new_n82_), .A1(new_n80_), .B0(i0), .Y(new_n83_));
  OAI220     g73(.A0(new_n60_), .A1(i4), .B0(new_n48_), .B1(i0), .Y(new_n84_));
  OAI220     g74(.A0(new_n49_), .A1(new_n11_), .B0(i8), .B1(new_n27_), .Y(new_n85_));
  AO220      g75(.A0(new_n85_), .A1(new_n21_), .B0(new_n84_), .B1(i3), .Y(new_n86_));
  OAI210     g76(.A0(new_n86_), .A1(new_n83_), .B0(i2), .Y(new_n87_));
  AOI210     g77(.A0(i7), .A1(new_n29_), .B0(new_n27_), .Y(new_n88_));
  OAI210     g78(.A0(i3), .A1(i0), .B0(new_n15_), .Y(new_n89_));
  AO210      g79(.A0(new_n88_), .A1(new_n55_), .B0(new_n89_), .Y(new_n90_));
  NO3        g80(.A(new_n71_), .B(new_n19_), .C(i4), .Y(new_n91_));
  OAI210     g81(.A0(new_n61_), .A1(new_n15_), .B0(new_n81_), .Y(new_n92_));
  AOI210     g82(.A0(new_n92_), .A1(new_n29_), .B0(new_n91_), .Y(new_n93_));
  AOI210     g83(.A0(new_n93_), .A1(new_n90_), .B0(new_n11_), .Y(new_n94_));
  NAi32      g84(.An(i8), .Bn(i7), .C(i5), .Y(new_n95_));
  AOI210     g85(.A0(new_n95_), .A1(new_n49_), .B0(new_n71_), .Y(new_n96_));
  NAi21      g86(.An(i5), .B(i4), .Y(new_n97_));
  AOI210     g87(.A0(new_n52_), .A1(new_n23_), .B0(new_n97_), .Y(new_n98_));
  NO3        g88(.A(new_n40_), .B(i7), .C(i6), .Y(new_n99_));
  NO3        g89(.A(new_n99_), .B(new_n98_), .C(new_n96_), .Y(new_n100_));
  NAi21      g90(.An(i0), .B(i6), .Y(new_n101_));
  OAI220     g91(.A0(new_n101_), .A1(i7), .B0(new_n55_), .B1(i3), .Y(new_n102_));
  AOI220     g92(.A0(new_n102_), .A1(i8), .B0(i7), .B1(new_n69_), .Y(new_n103_));
  OAI220     g93(.A0(new_n103_), .A1(new_n19_), .B0(new_n100_), .B1(new_n29_), .Y(new_n104_));
  OAI210     g94(.A0(new_n104_), .A1(new_n94_), .B0(new_n28_), .Y(new_n105_));
  NA4        g95(.A(new_n105_), .B(new_n87_), .C(new_n76_), .D(new_n45_), .Y(zori0));
endmodule


