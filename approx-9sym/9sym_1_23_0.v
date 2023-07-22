// Benchmark "9sym/9sym_1_23_0_esp" written by ABC on Wed Mar  8 02:21:37 2023

module \9sym/9sym_1_23_0_esp  ( 
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
    new_n88_;
  INV        g00(.A(i0), .Y(new_n11_));
  INV        g01(.A(i5), .Y(new_n12_));
  NAi21      g02(.An(i3), .B(i4), .Y(new_n13_));
  NAi21      g03(.An(i7), .B(i6), .Y(new_n14_));
  OAI210     g04(.A0(new_n14_), .A1(new_n12_), .B0(new_n13_), .Y(new_n15_));
  NA2        g05(.A(new_n15_), .B(new_n11_), .Y(new_n16_));
  INV        g06(.A(i7), .Y(new_n17_));
  OAI210     g07(.A0(new_n17_), .A1(i3), .B0(i6), .Y(new_n18_));
  NO2        g08(.A(i6), .B(i0), .Y(new_n19_));
  NO2        g09(.A(new_n19_), .B(i1), .Y(new_n20_));
  INV        g10(.A(i4), .Y(new_n21_));
  NO3        g11(.A(i7), .B(i5), .C(new_n21_), .Y(new_n22_));
  AOI210     g12(.A0(new_n20_), .A1(new_n18_), .B0(new_n22_), .Y(new_n23_));
  AOI210     g13(.A0(new_n23_), .A1(new_n16_), .B0(i2), .Y(new_n24_));
  OAI210     g14(.A0(i6), .A1(i4), .B0(i1), .Y(new_n25_));
  NAi21      g15(.An(i0), .B(i2), .Y(new_n26_));
  NO2        g16(.A(new_n26_), .B(i4), .Y(new_n27_));
  AOI210     g17(.A0(new_n25_), .A1(i0), .B0(new_n27_), .Y(new_n28_));
  NOi21      g18(.An(i7), .B(i5), .Y(new_n29_));
  NO2        g19(.A(i3), .B(i2), .Y(new_n30_));
  AO220      g20(.A0(new_n30_), .A1(new_n29_), .B0(new_n19_), .B1(i5), .Y(new_n31_));
  OAI210     g21(.A0(i4), .A1(i3), .B0(i1), .Y(new_n32_));
  NAi21      g22(.An(i4), .B(i2), .Y(new_n33_));
  AOI210     g23(.A0(new_n33_), .A1(new_n13_), .B0(i5), .Y(new_n34_));
  AOI220     g24(.A0(new_n34_), .A1(new_n32_), .B0(new_n31_), .B1(i1), .Y(new_n35_));
  OAI210     g25(.A0(new_n28_), .A1(i7), .B0(new_n35_), .Y(new_n36_));
  OAI210     g26(.A0(new_n36_), .A1(new_n24_), .B0(i8), .Y(new_n37_));
  NO2        g27(.A(i4), .B(i2), .Y(new_n38_));
  NO3        g28(.A(i8), .B(i7), .C(i0), .Y(new_n39_));
  OA210      g29(.A0(new_n39_), .A1(new_n38_), .B0(i1), .Y(new_n40_));
  NO3        g30(.A(new_n12_), .B(i3), .C(i1), .Y(new_n41_));
  NO2        g31(.A(new_n41_), .B(new_n29_), .Y(new_n42_));
  NO3        g32(.A(i8), .B(i4), .C(new_n11_), .Y(new_n43_));
  OAI220     g33(.A0(i8), .A1(i5), .B0(i7), .B1(i3), .Y(new_n44_));
  AOI210     g34(.A0(new_n44_), .A1(i4), .B0(new_n43_), .Y(new_n45_));
  OAI210     g35(.A0(new_n42_), .A1(new_n26_), .B0(new_n45_), .Y(new_n46_));
  OAI210     g36(.A0(new_n46_), .A1(new_n40_), .B0(i6), .Y(new_n47_));
  INV        g37(.A(i6), .Y(new_n48_));
  INV        g38(.A(i2), .Y(new_n49_));
  NAi21      g39(.An(i2), .B(i1), .Y(new_n50_));
  NAi21      g40(.An(i8), .B(i4), .Y(new_n51_));
  OA220      g41(.A0(new_n51_), .A1(new_n50_), .B0(i3), .B1(new_n49_), .Y(new_n52_));
  OAI210     g42(.A0(new_n17_), .A1(i3), .B0(new_n26_), .Y(new_n53_));
  INV        g43(.A(i1), .Y(new_n54_));
  NO4        g44(.A(new_n17_), .B(new_n49_), .C(new_n54_), .D(i0), .Y(new_n55_));
  AOI210     g45(.A0(new_n53_), .A1(i4), .B0(new_n55_), .Y(new_n56_));
  OAI210     g46(.A0(new_n52_), .A1(new_n11_), .B0(new_n56_), .Y(new_n57_));
  NO2        g47(.A(i3), .B(new_n54_), .Y(new_n58_));
  NO4        g48(.A(i8), .B(new_n17_), .C(i2), .D(new_n11_), .Y(new_n59_));
  AOI220     g49(.A0(new_n59_), .A1(new_n58_), .B0(new_n57_), .B1(new_n48_), .Y(new_n60_));
  OA210      g50(.A0(i4), .A1(i1), .B0(i5), .Y(new_n61_));
  NO2        g51(.A(new_n61_), .B(new_n11_), .Y(new_n62_));
  NAi31      g52(.An(i8), .B(i5), .C(i4), .Y(new_n63_));
  AOI210     g53(.A0(new_n63_), .A1(new_n50_), .B0(i7), .Y(new_n64_));
  OAI210     g54(.A0(new_n64_), .A1(new_n62_), .B0(new_n48_), .Y(new_n65_));
  NOi21      g55(.An(i6), .B(i7), .Y(new_n66_));
  NOi21      g56(.An(i5), .B(i8), .Y(new_n67_));
  AOI220     g57(.A0(new_n67_), .A1(new_n21_), .B0(new_n66_), .B1(new_n54_), .Y(new_n68_));
  AOI220     g58(.A0(new_n67_), .A1(new_n66_), .B0(new_n29_), .B1(new_n54_), .Y(new_n69_));
  MUX2       g59(.S(i2), .A(new_n69_), .B(new_n68_), .Y(new_n70_));
  NO2        g60(.A(i5), .B(new_n54_), .Y(new_n71_));
  INV        g61(.A(i8), .Y(new_n72_));
  NO4        g62(.A(new_n72_), .B(new_n12_), .C(i2), .D(i1), .Y(new_n73_));
  OAI210     g63(.A0(new_n73_), .A1(new_n71_), .B0(new_n11_), .Y(new_n74_));
  NO3        g64(.A(new_n14_), .B(new_n72_), .C(i5), .Y(new_n75_));
  NO3        g65(.A(new_n17_), .B(i1), .C(i0), .Y(new_n76_));
  OAI210     g66(.A0(new_n76_), .A1(new_n75_), .B0(new_n21_), .Y(new_n77_));
  NA4        g67(.A(new_n77_), .B(new_n74_), .C(new_n70_), .D(new_n65_), .Y(new_n78_));
  NO3        g68(.A(i8), .B(new_n17_), .C(i2), .Y(new_n79_));
  NA2        g69(.A(new_n51_), .B(new_n33_), .Y(new_n80_));
  AOI210     g70(.A0(new_n80_), .A1(new_n58_), .B0(new_n79_), .Y(new_n81_));
  OAI210     g71(.A0(i4), .A1(i3), .B0(i8), .Y(new_n82_));
  AO210      g72(.A0(new_n82_), .A1(new_n54_), .B0(new_n38_), .Y(new_n83_));
  OAI210     g73(.A0(i7), .A1(i3), .B0(i1), .Y(new_n84_));
  AOI210     g74(.A0(new_n51_), .A1(new_n54_), .B0(new_n11_), .Y(new_n85_));
  AOI220     g75(.A0(new_n85_), .A1(new_n84_), .B0(new_n83_), .B1(i7), .Y(new_n86_));
  OAI210     g76(.A0(new_n81_), .A1(i0), .B0(new_n86_), .Y(new_n87_));
  AOI220     g77(.A0(new_n87_), .A1(i5), .B0(new_n78_), .B1(i3), .Y(new_n88_));
  NA4        g78(.A(new_n88_), .B(new_n60_), .C(new_n47_), .D(new_n37_), .Y(zori0));
endmodule


