// Benchmark "9sym/9sym_1_51_0_esp" written by ABC on Wed Mar  8 02:28:37 2023

module \9sym/9sym_1_51_0_esp  ( 
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
    new_n67_, new_n68_;
  INV        g00(.A(i2), .Y(new_n11_));
  INV        g01(.A(i0), .Y(new_n12_));
  NO2        g02(.A(i6), .B(new_n12_), .Y(new_n13_));
  INV        g03(.A(i5), .Y(new_n14_));
  INV        g04(.A(i6), .Y(new_n15_));
  NO3        g05(.A(i8), .B(new_n15_), .C(new_n14_), .Y(new_n16_));
  OAI210     g06(.A0(new_n16_), .A1(new_n13_), .B0(i3), .Y(new_n17_));
  NA3        g07(.A(i8), .B(new_n14_), .C(i4), .Y(new_n18_));
  AOI210     g08(.A0(new_n18_), .A1(new_n17_), .B0(i7), .Y(new_n19_));
  INV        g09(.A(i1), .Y(new_n20_));
  INV        g10(.A(i7), .Y(new_n21_));
  INV        g11(.A(i4), .Y(new_n22_));
  NAi21      g12(.An(i5), .B(i3), .Y(new_n23_));
  NAi21      g13(.An(i3), .B(i8), .Y(new_n24_));
  OAI210     g14(.A0(new_n24_), .A1(new_n15_), .B0(new_n23_), .Y(new_n25_));
  AOI220     g15(.A0(new_n25_), .A1(new_n20_), .B0(i5), .B1(new_n22_), .Y(new_n26_));
  NAi21      g16(.An(i4), .B(i6), .Y(new_n27_));
  NAi21      g17(.An(i5), .B(i7), .Y(new_n28_));
  OA210      g18(.A0(new_n28_), .A1(new_n24_), .B0(new_n27_), .Y(new_n29_));
  OAI220     g19(.A0(new_n29_), .A1(new_n20_), .B0(new_n26_), .B1(new_n21_), .Y(new_n30_));
  OAI210     g20(.A0(new_n30_), .A1(new_n19_), .B0(new_n11_), .Y(new_n31_));
  INV        g21(.A(i8), .Y(new_n32_));
  NO2        g22(.A(new_n32_), .B(i6), .Y(new_n33_));
  NO3        g23(.A(i8), .B(new_n22_), .C(i3), .Y(new_n34_));
  NO2        g24(.A(new_n14_), .B(new_n20_), .Y(new_n35_));
  OAI210     g25(.A0(new_n34_), .A1(new_n33_), .B0(new_n35_), .Y(new_n36_));
  NO2        g26(.A(i8), .B(i4), .Y(new_n37_));
  AN2        g27(.A(i6), .B(i4), .Y(new_n38_));
  OAI220     g28(.A0(new_n38_), .A1(new_n37_), .B0(new_n28_), .B1(new_n15_), .Y(new_n39_));
  AO210      g29(.A0(i8), .A1(i1), .B0(i2), .Y(new_n40_));
  NA2        g30(.A(new_n40_), .B(i5), .Y(new_n41_));
  INV        g31(.A(i3), .Y(new_n42_));
  AOI210     g32(.A0(i8), .A1(i5), .B0(i1), .Y(new_n43_));
  NO2        g33(.A(new_n43_), .B(new_n42_), .Y(new_n44_));
  AOI220     g34(.A0(new_n44_), .A1(new_n41_), .B0(new_n39_), .B1(i2), .Y(new_n45_));
  AOI210     g35(.A0(new_n45_), .A1(new_n36_), .B0(i0), .Y(new_n46_));
  NA2        g36(.A(i3), .B(i2), .Y(new_n47_));
  OAI220     g37(.A0(new_n47_), .A1(new_n15_), .B0(new_n32_), .B1(new_n12_), .Y(new_n48_));
  NA2        g38(.A(new_n48_), .B(new_n20_), .Y(new_n49_));
  NA2        g39(.A(new_n38_), .B(new_n42_), .Y(new_n50_));
  AOI210     g40(.A0(new_n50_), .A1(new_n49_), .B0(i7), .Y(new_n51_));
  NOi21      g41(.An(new_n37_), .B(new_n11_), .Y(new_n52_));
  NO3        g42(.A(i7), .B(i3), .C(new_n12_), .Y(new_n53_));
  OAI210     g43(.A0(new_n53_), .A1(new_n52_), .B0(i1), .Y(new_n54_));
  OAI210     g44(.A0(i4), .A1(i3), .B0(i8), .Y(new_n55_));
  NO2        g45(.A(new_n21_), .B(i1), .Y(new_n56_));
  NOi21      g46(.An(new_n37_), .B(new_n47_), .Y(new_n57_));
  AOI210     g47(.A0(new_n56_), .A1(new_n55_), .B0(new_n57_), .Y(new_n58_));
  AOI210     g48(.A0(new_n58_), .A1(new_n54_), .B0(new_n14_), .Y(new_n59_));
  OA210      g49(.A0(i5), .A1(i4), .B0(i6), .Y(new_n60_));
  NO3        g50(.A(new_n60_), .B(i3), .C(new_n11_), .Y(new_n61_));
  OAI220     g51(.A0(new_n27_), .A1(i8), .B0(new_n23_), .B1(i6), .Y(new_n62_));
  OAI210     g52(.A0(new_n62_), .A1(new_n61_), .B0(i0), .Y(new_n63_));
  NO3        g53(.A(i8), .B(new_n15_), .C(i5), .Y(new_n64_));
  NO2        g54(.A(i6), .B(i3), .Y(new_n65_));
  AOI210     g55(.A0(new_n65_), .A1(i7), .B0(new_n64_), .Y(new_n66_));
  OAI210     g56(.A0(new_n66_), .A1(new_n22_), .B0(new_n63_), .Y(new_n67_));
  NO4        g57(.A(new_n67_), .B(new_n59_), .C(new_n51_), .D(new_n46_), .Y(new_n68_));
  NA2        g58(.A(new_n68_), .B(new_n31_), .Y(zori0));
endmodule


