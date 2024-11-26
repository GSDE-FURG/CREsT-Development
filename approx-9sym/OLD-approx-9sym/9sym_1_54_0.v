// Benchmark "9sym/9sym_1_54_0_esp" written by ABC on Wed Mar  8 02:29:01 2023

module \9sym/9sym_1_54_0_esp  ( 
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
    new_n60_, new_n61_, new_n62_, new_n63_, new_n64_, new_n65_, new_n66_;
  NAi21      g00(.An(i4), .B(i5), .Y(new_n11_));
  NAi31      g01(.An(i2), .B(i8), .C(i6), .Y(new_n12_));
  AOI210     g02(.A0(new_n12_), .A1(new_n11_), .B0(i1), .Y(new_n13_));
  INV        g03(.A(i4), .Y(new_n14_));
  NAi21      g04(.An(i2), .B(i8), .Y(new_n15_));
  NAi21      g05(.An(i5), .B(i1), .Y(new_n16_));
  OAI220     g06(.A0(new_n16_), .A1(new_n15_), .B0(i6), .B1(new_n14_), .Y(new_n17_));
  OAI210     g07(.A0(new_n17_), .A1(new_n13_), .B0(i7), .Y(new_n18_));
  NA2        g08(.A(i6), .B(i4), .Y(new_n19_));
  NA3        g09(.A(i5), .B(i1), .C(i0), .Y(new_n20_));
  AOI210     g10(.A0(new_n20_), .A1(new_n19_), .B0(i7), .Y(new_n21_));
  OAI210     g11(.A0(i5), .A1(i4), .B0(i6), .Y(new_n22_));
  INV        g12(.A(i0), .Y(new_n23_));
  INV        g13(.A(i2), .Y(new_n24_));
  NO2        g14(.A(new_n24_), .B(new_n23_), .Y(new_n25_));
  AOI210     g15(.A0(new_n25_), .A1(new_n22_), .B0(new_n21_), .Y(new_n26_));
  AOI210     g16(.A0(new_n26_), .A1(new_n18_), .B0(i3), .Y(new_n27_));
  NAi31      g17(.An(i7), .B(i8), .C(i4), .Y(new_n28_));
  NAi31      g18(.An(i1), .B(i7), .C(i3), .Y(new_n29_));
  AOI210     g19(.A0(new_n29_), .A1(new_n28_), .B0(i2), .Y(new_n30_));
  NA2        g20(.A(i3), .B(i1), .Y(new_n31_));
  NA3        g21(.A(i7), .B(i6), .C(i2), .Y(new_n32_));
  AOI210     g22(.A0(new_n32_), .A1(new_n31_), .B0(i0), .Y(new_n33_));
  NA2        g23(.A(i3), .B(i0), .Y(new_n34_));
  NO2        g24(.A(new_n34_), .B(i6), .Y(new_n35_));
  NO2        g25(.A(new_n19_), .B(i8), .Y(new_n36_));
  NO4        g26(.A(new_n36_), .B(new_n35_), .C(new_n33_), .D(new_n30_), .Y(new_n37_));
  NO2        g27(.A(new_n37_), .B(i5), .Y(new_n38_));
  INV        g28(.A(i5), .Y(new_n39_));
  INV        g29(.A(i8), .Y(new_n40_));
  NO2        g30(.A(i3), .B(i1), .Y(new_n41_));
  NO3        g31(.A(new_n41_), .B(i4), .C(new_n24_), .Y(new_n42_));
  INV        g32(.A(i7), .Y(new_n43_));
  NAi21      g33(.An(i2), .B(i3), .Y(new_n44_));
  NAi21      g34(.An(i7), .B(i6), .Y(new_n45_));
  OAI220     g35(.A0(new_n45_), .A1(new_n44_), .B0(new_n43_), .B1(i1), .Y(new_n46_));
  OAI210     g36(.A0(new_n46_), .A1(new_n42_), .B0(new_n40_), .Y(new_n47_));
  INV        g37(.A(i1), .Y(new_n48_));
  NA2        g38(.A(new_n44_), .B(new_n48_), .Y(new_n49_));
  AN2        g39(.A(i6), .B(i1), .Y(new_n50_));
  NO3        g40(.A(new_n50_), .B(new_n40_), .C(i0), .Y(new_n51_));
  NO2        g41(.A(i4), .B(i2), .Y(new_n52_));
  AOI220     g42(.A0(new_n52_), .A1(i7), .B0(new_n51_), .B1(new_n49_), .Y(new_n53_));
  AOI210     g43(.A0(new_n53_), .A1(new_n47_), .B0(new_n39_), .Y(new_n54_));
  NAi21      g44(.An(i4), .B(i6), .Y(new_n55_));
  NAi21      g45(.An(i0), .B(i3), .Y(new_n56_));
  OAI210     g46(.A0(new_n56_), .A1(i8), .B0(new_n55_), .Y(new_n57_));
  AOI220     g47(.A0(new_n57_), .A1(i1), .B0(new_n35_), .B1(new_n43_), .Y(new_n58_));
  NAi21      g48(.An(i7), .B(i8), .Y(new_n59_));
  OAI220     g49(.A0(new_n55_), .A1(i8), .B0(new_n59_), .B1(i1), .Y(new_n60_));
  NAi21      g50(.An(i1), .B(i3), .Y(new_n61_));
  NO2        g51(.A(i8), .B(i4), .Y(new_n62_));
  AO210      g52(.A0(i6), .A1(i4), .B0(i0), .Y(new_n63_));
  OAI220     g53(.A0(new_n63_), .A1(new_n62_), .B0(new_n61_), .B1(new_n45_), .Y(new_n64_));
  AOI220     g54(.A0(new_n64_), .A1(i2), .B0(new_n60_), .B1(i0), .Y(new_n65_));
  OAI210     g55(.A0(new_n58_), .A1(i2), .B0(new_n65_), .Y(new_n66_));
  OR4        g56(.A(new_n66_), .B(new_n54_), .C(new_n38_), .D(new_n27_), .Y(zori0));
endmodule


