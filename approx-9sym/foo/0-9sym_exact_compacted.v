// Benchmark "teste" written by ABC on Wed Jul 26 20:46:58 2023

module teste ( 
    i_7_, i_8_, i_5_, i_6_, i_3_, i_4_, i_1_, i_2_, i_0_,
    o_0_  );
  input  i_7_, i_8_, i_5_, i_6_, i_3_, i_4_, i_1_, i_2_, i_0_;
  output o_0_;
  wire new_n11_, new_n12_, new_n13_, new_n14_, new_n15_, new_n16_, new_n17_,
    new_n18_, new_n19_, new_n20_, new_n21_, new_n22_, new_n23_, new_n24_,
    new_n25_, new_n26_, new_n27_, new_n28_, new_n29_, new_n30_, new_n31_,
    new_n32_, new_n33_, new_n34_, new_n35_, new_n36_, new_n37_, new_n38_,
    new_n39_, new_n40_, new_n41_, new_n42_, new_n43_, new_n44_, new_n45_,
    new_n46_, new_n47_, new_n48_, new_n49_, new_n50_, new_n51_, new_n52_,
    new_n53_, new_n54_, new_n55_, new_n56_;
  INV        g00(.A(i_0_), .Y(new_n11_));
  NO2        g01(.A(new_n11_), .B(i_2_), .Y(new_n12_));
  INV        g02(.A(i_2_), .Y(new_n13_));
  NO2        g03(.A(i_0_), .B(new_n13_), .Y(new_n14_));
  INV        g04(.A(i_7_), .Y(new_n15_));
  NA2        g05(.A(i_3_), .B(i_6_), .Y(new_n16_));
  NA2        g06(.A(i_5_), .B(i_8_), .Y(new_n17_));
  OAI210     g07(.A0(new_n17_), .A1(new_n16_), .B0(new_n15_), .Y(new_n18_));
  INV        g08(.A(i_8_), .Y(new_n19_));
  INV        g09(.A(i_5_), .Y(new_n20_));
  OAI210     g10(.A0(new_n16_), .A1(new_n20_), .B0(new_n19_), .Y(new_n21_));
  NO2        g11(.A(i_3_), .B(i_6_), .Y(new_n22_));
  AOI210     g12(.A0(new_n16_), .A1(new_n20_), .B0(new_n22_), .Y(new_n23_));
  AN3        g13(.A(new_n23_), .B(new_n21_), .C(new_n18_), .Y(new_n24_));
  NA2        g14(.A(i_1_), .B(i_4_), .Y(new_n25_));
  NO2        g15(.A(new_n25_), .B(new_n24_), .Y(new_n26_));
  XN2        g16(.A(i_1_), .B(i_4_), .Y(new_n27_));
  NA4        g17(.A(i_6_), .B(i_5_), .C(i_8_), .D(i_7_), .Y(new_n28_));
  NO4        g18(.A(i_6_), .B(i_5_), .C(i_8_), .D(i_7_), .Y(new_n29_));
  NO2        g19(.A(new_n29_), .B(i_3_), .Y(new_n30_));
  AOI210     g20(.A0(new_n28_), .A1(i_3_), .B0(new_n30_), .Y(new_n31_));
  NO2        g21(.A(i_5_), .B(i_8_), .Y(new_n32_));
  AOI210     g22(.A0(new_n32_), .A1(new_n22_), .B0(new_n15_), .Y(new_n33_));
  AOI210     g23(.A0(new_n22_), .A1(new_n20_), .B0(new_n19_), .Y(new_n34_));
  OAI210     g24(.A0(new_n22_), .A1(new_n20_), .B0(new_n16_), .Y(new_n35_));
  NO3        g25(.A(new_n35_), .B(new_n34_), .C(new_n33_), .Y(new_n36_));
  OR2        g26(.A(i_1_), .B(i_4_), .Y(new_n37_));
  OAI220     g27(.A0(new_n37_), .A1(new_n36_), .B0(new_n31_), .B1(new_n27_), .Y(new_n38_));
  OAI220     g28(.A0(new_n38_), .A1(new_n26_), .B0(new_n14_), .B1(new_n12_), .Y(new_n39_));
  NA2        g29(.A(new_n11_), .B(new_n13_), .Y(new_n40_));
  NA2        g30(.A(i_0_), .B(i_2_), .Y(new_n41_));
  OAI220     g31(.A0(new_n41_), .A1(new_n24_), .B0(new_n40_), .B1(new_n36_), .Y(new_n42_));
  NAi21      g32(.An(new_n27_), .B(new_n42_), .Y(new_n43_));
  AOI210     g33(.A0(new_n23_), .A1(new_n21_), .B0(i_7_), .Y(new_n44_));
  NA2        g34(.A(new_n22_), .B(new_n20_), .Y(new_n45_));
  OAI210     g35(.A0(new_n23_), .A1(i_8_), .B0(new_n45_), .Y(new_n46_));
  AN3        g36(.A(i_0_), .B(i_2_), .C(i_1_), .Y(new_n47_));
  OAI210     g37(.A0(new_n46_), .A1(new_n44_), .B0(new_n47_), .Y(new_n48_));
  OAI220     g38(.A0(new_n41_), .A1(new_n37_), .B0(new_n40_), .B1(new_n25_), .Y(new_n49_));
  NOi21      g39(.An(new_n49_), .B(new_n31_), .Y(new_n50_));
  OAI210     g40(.A0(new_n35_), .A1(new_n34_), .B0(i_7_), .Y(new_n51_));
  NO2        g41(.A(new_n16_), .B(new_n20_), .Y(new_n52_));
  AOI210     g42(.A0(new_n35_), .A1(i_8_), .B0(new_n52_), .Y(new_n53_));
  NA2        g43(.A(new_n53_), .B(new_n51_), .Y(new_n54_));
  NO3        g44(.A(i_0_), .B(i_2_), .C(i_1_), .Y(new_n55_));
  AOI210     g45(.A0(new_n55_), .A1(new_n54_), .B0(new_n50_), .Y(new_n56_));
  NA4        g46(.A(new_n56_), .B(new_n48_), .C(new_n43_), .D(new_n39_), .Y(o_0_));
endmodule


