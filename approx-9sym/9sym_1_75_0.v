// Benchmark "9sym/9sym_1_75_0_esp" written by ABC on Wed Mar  8 02:34:23 2023

module \9sym/9sym_1_75_0_esp  ( 
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
    new_n53_, new_n54_, new_n55_;
  INV        g00(.A(i3), .Y(new_n11_));
  INV        g01(.A(i7), .Y(new_n12_));
  INV        g02(.A(i1), .Y(new_n13_));
  INV        g03(.A(i6), .Y(new_n14_));
  INV        g04(.A(i4), .Y(new_n15_));
  NOi21      g05(.An(i8), .B(i2), .Y(new_n16_));
  AO220      g06(.A0(new_n16_), .A1(i6), .B0(i5), .B1(new_n15_), .Y(new_n17_));
  AOI220     g07(.A0(new_n17_), .A1(new_n13_), .B0(new_n14_), .B1(i4), .Y(new_n18_));
  INV        g08(.A(i2), .Y(new_n19_));
  NA2        g09(.A(i5), .B(i1), .Y(new_n20_));
  OAI220     g10(.A0(new_n20_), .A1(i7), .B0(i6), .B1(new_n19_), .Y(new_n21_));
  NOi21      g11(.An(i6), .B(i7), .Y(new_n22_));
  AOI220     g12(.A0(new_n22_), .A1(i4), .B0(new_n21_), .B1(i0), .Y(new_n23_));
  OAI210     g13(.A0(new_n18_), .A1(new_n12_), .B0(new_n23_), .Y(new_n24_));
  OAI210     g14(.A0(i8), .A1(i2), .B0(i5), .Y(new_n25_));
  NOi21      g15(.An(i5), .B(i1), .Y(new_n26_));
  AOI220     g16(.A0(new_n26_), .A1(new_n16_), .B0(new_n25_), .B1(i1), .Y(new_n27_));
  NO2        g17(.A(new_n27_), .B(i0), .Y(new_n28_));
  INV        g18(.A(i5), .Y(new_n29_));
  NO2        g19(.A(new_n22_), .B(i2), .Y(new_n30_));
  NO2        g20(.A(new_n15_), .B(new_n19_), .Y(new_n31_));
  NO4        g21(.A(new_n31_), .B(new_n30_), .C(i8), .D(new_n29_), .Y(new_n32_));
  OAI210     g22(.A0(i7), .A1(i2), .B0(i5), .Y(new_n33_));
  NA3        g23(.A(new_n33_), .B(new_n14_), .C(i0), .Y(new_n34_));
  AOI210     g24(.A0(new_n29_), .A1(new_n19_), .B0(new_n22_), .Y(new_n35_));
  OAI210     g25(.A0(i7), .A1(i2), .B0(new_n13_), .Y(new_n36_));
  OAI210     g26(.A0(new_n36_), .A1(new_n35_), .B0(new_n34_), .Y(new_n37_));
  NO3        g27(.A(new_n37_), .B(new_n32_), .C(new_n28_), .Y(new_n38_));
  NOi21      g28(.An(i6), .B(i5), .Y(new_n39_));
  AOI220     g29(.A0(new_n39_), .A1(i7), .B0(i8), .B1(new_n15_), .Y(new_n40_));
  INV        g30(.A(i8), .Y(new_n41_));
  NA4        g31(.A(new_n41_), .B(i5), .C(new_n15_), .D(i1), .Y(new_n42_));
  OAI210     g32(.A0(new_n40_), .A1(i0), .B0(new_n42_), .Y(new_n43_));
  NOi31      g33(.An(i2), .B(i6), .C(i0), .Y(new_n44_));
  NOi31      g34(.An(i6), .B(i8), .C(i5), .Y(new_n45_));
  OAI210     g35(.A0(new_n45_), .A1(new_n44_), .B0(i4), .Y(new_n46_));
  NOi31      g36(.An(i6), .B(i8), .C(i4), .Y(new_n47_));
  NOi31      g37(.An(i8), .B(i7), .C(i1), .Y(new_n48_));
  OAI210     g38(.A0(new_n48_), .A1(new_n47_), .B0(i0), .Y(new_n49_));
  AO220      g39(.A0(i7), .A1(i5), .B0(i6), .B1(i1), .Y(new_n50_));
  NA3        g40(.A(new_n50_), .B(new_n15_), .C(new_n19_), .Y(new_n51_));
  NA3        g41(.A(new_n26_), .B(new_n41_), .C(i7), .Y(new_n52_));
  NA4        g42(.A(new_n52_), .B(new_n51_), .C(new_n49_), .D(new_n46_), .Y(new_n53_));
  AOI210     g43(.A0(new_n43_), .A1(i2), .B0(new_n53_), .Y(new_n54_));
  OAI210     g44(.A0(new_n38_), .A1(new_n11_), .B0(new_n54_), .Y(new_n55_));
  AO210      g45(.A0(new_n24_), .A1(new_n11_), .B0(new_n55_), .Y(zori0));
endmodule


