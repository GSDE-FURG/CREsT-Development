// Benchmark "9sym/9sym_1_17_0_esp" written by ABC on Wed Mar  8 02:20:02 2023

module \9sym/9sym_1_17_0_esp  ( 
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
    new_n102_, new_n103_, new_n104_, new_n105_, new_n106_;
  INV        g00(.A(i2), .Y(new_n11_));
  INV        g01(.A(i4), .Y(new_n12_));
  INV        g02(.A(i8), .Y(new_n13_));
  NO3        g03(.A(new_n13_), .B(new_n12_), .C(i0), .Y(new_n14_));
  INV        g04(.A(i0), .Y(new_n15_));
  NO2        g05(.A(i8), .B(i7), .Y(new_n16_));
  NA2        g06(.A(i8), .B(i5), .Y(new_n17_));
  NOi31      g07(.An(new_n17_), .B(new_n16_), .C(new_n15_), .Y(new_n18_));
  OAI210     g08(.A0(new_n18_), .A1(new_n14_), .B0(new_n11_), .Y(new_n19_));
  OAI210     g09(.A0(new_n12_), .A1(i0), .B0(new_n13_), .Y(new_n20_));
  NA2        g10(.A(i8), .B(i6), .Y(new_n21_));
  NA3        g11(.A(new_n21_), .B(new_n20_), .C(i5), .Y(new_n22_));
  AOI210     g12(.A0(new_n22_), .A1(new_n19_), .B0(i3), .Y(new_n23_));
  INV        g13(.A(i5), .Y(new_n24_));
  NAi21      g14(.An(i2), .B(i0), .Y(new_n25_));
  NAi21      g15(.An(i6), .B(i7), .Y(new_n26_));
  NAi21      g16(.An(i5), .B(i8), .Y(new_n27_));
  OAI220     g17(.A0(new_n27_), .A1(new_n26_), .B0(new_n25_), .B1(new_n24_), .Y(new_n28_));
  NA2        g18(.A(new_n28_), .B(new_n12_), .Y(new_n29_));
  INV        g19(.A(i6), .Y(new_n30_));
  NO2        g20(.A(new_n30_), .B(i0), .Y(new_n31_));
  OAI210     g21(.A0(new_n16_), .A1(new_n24_), .B0(new_n31_), .Y(new_n32_));
  OAI220     g22(.A0(i8), .A1(i5), .B0(i6), .B1(i0), .Y(new_n33_));
  NA2        g23(.A(new_n33_), .B(i3), .Y(new_n34_));
  NO2        g24(.A(i6), .B(new_n12_), .Y(new_n35_));
  NO2        g25(.A(new_n25_), .B(i8), .Y(new_n36_));
  NA2        g26(.A(new_n36_), .B(new_n35_), .Y(new_n37_));
  NA4        g27(.A(new_n37_), .B(new_n34_), .C(new_n32_), .D(new_n29_), .Y(new_n38_));
  OAI210     g28(.A0(new_n38_), .A1(new_n23_), .B0(i1), .Y(new_n39_));
  INV        g29(.A(i1), .Y(new_n40_));
  OR2        g30(.A(i5), .B(i3), .Y(new_n41_));
  NO2        g31(.A(i6), .B(i3), .Y(new_n42_));
  AO210      g32(.A0(i5), .A1(i3), .B0(i2), .Y(new_n43_));
  OAI220     g33(.A0(new_n43_), .A1(new_n42_), .B0(new_n41_), .B1(new_n11_), .Y(new_n44_));
  NA2        g34(.A(new_n44_), .B(i7), .Y(new_n45_));
  NAi21      g35(.An(i7), .B(i5), .Y(new_n46_));
  OAI210     g36(.A0(new_n26_), .A1(i2), .B0(new_n46_), .Y(new_n47_));
  NAi32      g37(.An(i5), .Bn(i4), .C(i6), .Y(new_n48_));
  NAi21      g38(.An(i7), .B(i3), .Y(new_n49_));
  NA3        g39(.A(new_n49_), .B(new_n48_), .C(i2), .Y(new_n50_));
  AN2        g40(.A(new_n43_), .B(new_n25_), .Y(new_n51_));
  AOI220     g41(.A0(new_n51_), .A1(new_n50_), .B0(new_n47_), .B1(i0), .Y(new_n52_));
  AOI210     g42(.A0(new_n52_), .A1(new_n45_), .B0(new_n13_), .Y(new_n53_));
  INV        g43(.A(i7), .Y(new_n54_));
  NA2        g44(.A(i6), .B(i5), .Y(new_n55_));
  NAi21      g45(.An(i4), .B(i3), .Y(new_n56_));
  OAI220     g46(.A0(new_n56_), .A1(new_n54_), .B0(new_n55_), .B1(i3), .Y(new_n57_));
  INV        g47(.A(i3), .Y(new_n58_));
  NAi21      g48(.An(i6), .B(i0), .Y(new_n59_));
  NAi21      g49(.An(i8), .B(i6), .Y(new_n60_));
  OAI220     g50(.A0(new_n60_), .A1(new_n58_), .B0(new_n59_), .B1(i5), .Y(new_n61_));
  AOI210     g51(.A0(new_n57_), .A1(new_n15_), .B0(new_n61_), .Y(new_n62_));
  NA2        g52(.A(i5), .B(i4), .Y(new_n63_));
  OAI220     g53(.A0(new_n63_), .A1(i8), .B0(new_n56_), .B1(new_n26_), .Y(new_n64_));
  NAi21      g54(.An(i4), .B(i7), .Y(new_n65_));
  NAi21      g55(.An(i3), .B(i5), .Y(new_n66_));
  NO2        g56(.A(new_n66_), .B(new_n65_), .Y(new_n67_));
  AOI210     g57(.A0(new_n64_), .A1(i0), .B0(new_n67_), .Y(new_n68_));
  OAI210     g58(.A0(new_n62_), .A1(new_n11_), .B0(new_n68_), .Y(new_n69_));
  OAI210     g59(.A0(new_n69_), .A1(new_n53_), .B0(new_n40_), .Y(new_n70_));
  AOI210     g60(.A0(new_n17_), .A1(new_n30_), .B0(i3), .Y(new_n71_));
  NAi31      g61(.An(i8), .B(i5), .C(i3), .Y(new_n72_));
  OAI210     g62(.A0(new_n27_), .A1(new_n15_), .B0(new_n72_), .Y(new_n73_));
  AOI210     g63(.A0(new_n73_), .A1(new_n30_), .B0(new_n71_), .Y(new_n74_));
  NO2        g64(.A(new_n26_), .B(i3), .Y(new_n75_));
  OAI210     g65(.A0(new_n60_), .A1(i5), .B0(i4), .Y(new_n76_));
  NO2        g66(.A(new_n76_), .B(new_n75_), .Y(new_n77_));
  OAI210     g67(.A0(new_n74_), .A1(i7), .B0(new_n77_), .Y(new_n78_));
  NAi21      g68(.An(i7), .B(i8), .Y(new_n79_));
  AOI210     g69(.A0(new_n30_), .A1(new_n15_), .B0(new_n79_), .Y(new_n80_));
  NA3        g70(.A(new_n80_), .B(new_n55_), .C(i3), .Y(new_n81_));
  NA2        g71(.A(new_n41_), .B(i8), .Y(new_n82_));
  NA3        g72(.A(new_n82_), .B(i6), .C(i0), .Y(new_n83_));
  NA3        g73(.A(new_n83_), .B(new_n81_), .C(new_n12_), .Y(new_n84_));
  NAi32      g74(.An(i8), .Bn(i7), .C(i5), .Y(new_n85_));
  AOI210     g75(.A0(new_n85_), .A1(new_n65_), .B0(new_n30_), .Y(new_n86_));
  NAi21      g76(.An(i5), .B(i4), .Y(new_n87_));
  AOI210     g77(.A0(new_n87_), .A1(new_n46_), .B0(new_n59_), .Y(new_n88_));
  NO2        g78(.A(new_n87_), .B(new_n79_), .Y(new_n89_));
  NO3        g79(.A(new_n89_), .B(new_n88_), .C(new_n86_), .Y(new_n90_));
  NA2        g80(.A(i7), .B(i4), .Y(new_n91_));
  OAI220     g81(.A0(new_n91_), .A1(i8), .B0(new_n46_), .B1(new_n21_), .Y(new_n92_));
  OAI210     g82(.A0(new_n65_), .A1(new_n24_), .B0(new_n11_), .Y(new_n93_));
  AOI210     g83(.A0(new_n92_), .A1(new_n15_), .B0(new_n93_), .Y(new_n94_));
  OAI210     g84(.A0(new_n90_), .A1(new_n58_), .B0(new_n94_), .Y(new_n95_));
  NAi21      g85(.An(i4), .B(i1), .Y(new_n96_));
  AOI210     g86(.A0(new_n79_), .A1(new_n66_), .B0(new_n96_), .Y(new_n97_));
  OAI210     g87(.A0(new_n97_), .A1(new_n35_), .B0(new_n15_), .Y(new_n98_));
  NO3        g88(.A(new_n30_), .B(i5), .C(i0), .Y(new_n99_));
  NOi21      g89(.An(new_n42_), .B(new_n40_), .Y(new_n100_));
  OAI210     g90(.A0(new_n100_), .A1(new_n99_), .B0(i7), .Y(new_n101_));
  OAI220     g91(.A0(new_n17_), .A1(i6), .B0(i7), .B1(new_n15_), .Y(new_n102_));
  OAI210     g92(.A0(new_n72_), .A1(i4), .B0(i2), .Y(new_n103_));
  AOI210     g93(.A0(new_n102_), .A1(new_n58_), .B0(new_n103_), .Y(new_n104_));
  NA3        g94(.A(new_n104_), .B(new_n101_), .C(new_n98_), .Y(new_n105_));
  AOI220     g95(.A0(new_n105_), .A1(new_n95_), .B0(new_n84_), .B1(new_n78_), .Y(new_n106_));
  NA3        g96(.A(new_n106_), .B(new_n70_), .C(new_n39_), .Y(zori0));
endmodule


