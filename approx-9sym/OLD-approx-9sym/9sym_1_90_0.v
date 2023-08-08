// Benchmark "9sym/9sym_1_90_0_esp" written by ABC on Wed Mar  8 02:35:48 2023

module \9sym/9sym_1_90_0_esp  ( 
    i0, i1, i2, i3, i4, i5, i6, i7, i8,
    zori0  );
  input  i0, i1, i2, i3, i4, i5, i6, i7, i8;
  output zori0;
  wire new_n11_, new_n12_, new_n13_, new_n14_, new_n15_, new_n16_, new_n17_,
    new_n18_, new_n19_, new_n20_, new_n21_, new_n22_, new_n23_, new_n24_,
    new_n25_, new_n26_, new_n27_, new_n28_, new_n29_, new_n30_, new_n31_,
    new_n32_, new_n33_, new_n34_, new_n35_, new_n36_, new_n37_, new_n38_,
    new_n39_, new_n40_, new_n41_, new_n42_, new_n43_, new_n44_, new_n45_,
    new_n46_, new_n47_;
  INV        g00(.A(i5), .Y(new_n11_));
  NO2        g01(.A(i8), .B(new_n11_), .Y(new_n12_));
  NOi21      g02(.An(i2), .B(i4), .Y(new_n13_));
  INV        g03(.A(i6), .Y(new_n14_));
  NO3        g04(.A(i7), .B(new_n14_), .C(i2), .Y(new_n15_));
  OAI210     g05(.A0(new_n15_), .A1(new_n13_), .B0(new_n12_), .Y(new_n16_));
  INV        g06(.A(i0), .Y(new_n17_));
  OAI210     g07(.A0(i8), .A1(i2), .B0(i5), .Y(new_n18_));
  NA3        g08(.A(new_n18_), .B(i1), .C(new_n17_), .Y(new_n19_));
  INV        g09(.A(i1), .Y(new_n20_));
  OAI220     g10(.A0(i7), .A1(new_n14_), .B0(i5), .B1(i2), .Y(new_n21_));
  OR2        g11(.A(i7), .B(i2), .Y(new_n22_));
  NA3        g12(.A(new_n22_), .B(new_n21_), .C(new_n20_), .Y(new_n23_));
  NA2        g13(.A(new_n22_), .B(i5), .Y(new_n24_));
  NA3        g14(.A(new_n24_), .B(new_n14_), .C(i0), .Y(new_n25_));
  NA4        g15(.A(new_n25_), .B(new_n23_), .C(new_n19_), .D(new_n16_), .Y(new_n26_));
  NA2        g16(.A(new_n26_), .B(i3), .Y(new_n27_));
  OAI210     g17(.A0(i4), .A1(i3), .B0(i8), .Y(new_n28_));
  NO2        g18(.A(i4), .B(i2), .Y(new_n29_));
  AOI210     g19(.A0(new_n28_), .A1(new_n20_), .B0(new_n29_), .Y(new_n30_));
  NA2        g20(.A(new_n14_), .B(i4), .Y(new_n31_));
  OAI220     g21(.A0(new_n31_), .A1(i3), .B0(new_n30_), .B1(new_n11_), .Y(new_n32_));
  NA2        g22(.A(new_n32_), .B(i7), .Y(new_n33_));
  NA3        g23(.A(i7), .B(i6), .C(new_n11_), .Y(new_n34_));
  AOI210     g24(.A0(new_n34_), .A1(new_n31_), .B0(i0), .Y(new_n35_));
  NO4        g25(.A(i8), .B(new_n11_), .C(i4), .D(new_n20_), .Y(new_n36_));
  OAI210     g26(.A0(new_n36_), .A1(new_n35_), .B0(i2), .Y(new_n37_));
  OAI220     g27(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n38_));
  AOI220     g28(.A0(new_n38_), .A1(i4), .B0(new_n29_), .B1(i1), .Y(new_n39_));
  NA2        g29(.A(new_n13_), .B(new_n17_), .Y(new_n40_));
  NA2        g30(.A(new_n20_), .B(i0), .Y(new_n41_));
  OAI210     g31(.A0(new_n41_), .A1(i7), .B0(new_n40_), .Y(new_n42_));
  OAI210     g32(.A0(i8), .A1(i4), .B0(i6), .Y(new_n43_));
  NAi21      g33(.An(i3), .B(i2), .Y(new_n44_));
  AOI210     g34(.A0(new_n44_), .A1(new_n14_), .B0(new_n17_), .Y(new_n45_));
  AOI220     g35(.A0(new_n45_), .A1(new_n43_), .B0(new_n42_), .B1(i8), .Y(new_n46_));
  OA210      g36(.A0(new_n39_), .A1(new_n14_), .B0(new_n46_), .Y(new_n47_));
  NA4        g37(.A(new_n47_), .B(new_n37_), .C(new_n33_), .D(new_n27_), .Y(zori0));
endmodule


