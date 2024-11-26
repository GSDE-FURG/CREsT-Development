// Benchmark "9sym/9sym_1_27_0_esp" written by ABC on Wed Mar  8 02:22:29 2023

module \9sym/9sym_1_27_0_esp  ( 
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
  INV        g00(.A(i7), .Y(new_n11_));
  NO2        g01(.A(new_n11_), .B(i6), .Y(new_n12_));
  INV        g02(.A(i5), .Y(new_n13_));
  NO3        g03(.A(new_n13_), .B(i4), .C(i3), .Y(new_n14_));
  OAI210     g04(.A0(new_n14_), .A1(new_n12_), .B0(i2), .Y(new_n15_));
  INV        g05(.A(i6), .Y(new_n16_));
  NOi31      g06(.An(i4), .B(i8), .C(i3), .Y(new_n17_));
  AOI210     g07(.A0(i8), .A1(new_n16_), .B0(new_n17_), .Y(new_n18_));
  OAI210     g08(.A0(i8), .A1(i2), .B0(i5), .Y(new_n19_));
  NOi31      g09(.An(i6), .B(i8), .C(i7), .Y(new_n20_));
  AOI210     g10(.A0(new_n19_), .A1(i3), .B0(new_n20_), .Y(new_n21_));
  OA210      g11(.A0(new_n18_), .A1(new_n13_), .B0(new_n21_), .Y(new_n22_));
  AOI210     g12(.A0(new_n22_), .A1(new_n15_), .B0(i0), .Y(new_n23_));
  NAi21      g13(.An(i3), .B(i7), .Y(new_n24_));
  NAi21      g14(.An(i6), .B(i4), .Y(new_n25_));
  NAi21      g15(.An(i8), .B(i0), .Y(new_n26_));
  AOI210     g16(.A0(new_n25_), .A1(new_n24_), .B0(new_n26_), .Y(new_n27_));
  NAi21      g17(.An(i5), .B(i8), .Y(new_n28_));
  NO2        g18(.A(new_n28_), .B(new_n24_), .Y(new_n29_));
  INV        g19(.A(i3), .Y(new_n30_));
  OR2        g20(.A(i7), .B(i6), .Y(new_n31_));
  NO2        g21(.A(new_n31_), .B(new_n30_), .Y(new_n32_));
  NO2        g22(.A(new_n16_), .B(i4), .Y(new_n33_));
  NO4        g23(.A(new_n33_), .B(new_n32_), .C(new_n29_), .D(new_n27_), .Y(new_n34_));
  NA4        g24(.A(new_n11_), .B(i5), .C(new_n30_), .D(i0), .Y(new_n35_));
  OAI210     g25(.A0(new_n34_), .A1(i2), .B0(new_n35_), .Y(new_n36_));
  OAI210     g26(.A0(new_n36_), .A1(new_n23_), .B0(i1), .Y(new_n37_));
  INV        g27(.A(i0), .Y(new_n38_));
  NO2        g28(.A(new_n11_), .B(i4), .Y(new_n39_));
  INV        g29(.A(i8), .Y(new_n40_));
  NO3        g30(.A(new_n40_), .B(new_n13_), .C(i2), .Y(new_n41_));
  OAI210     g31(.A0(new_n41_), .A1(new_n39_), .B0(new_n38_), .Y(new_n42_));
  INV        g32(.A(i2), .Y(new_n43_));
  NO3        g33(.A(i7), .B(new_n16_), .C(new_n43_), .Y(new_n44_));
  NO3        g34(.A(new_n11_), .B(i5), .C(i2), .Y(new_n45_));
  NO2        g35(.A(new_n45_), .B(new_n44_), .Y(new_n46_));
  AOI210     g36(.A0(new_n46_), .A1(new_n42_), .B0(i1), .Y(new_n47_));
  INV        g37(.A(i4), .Y(new_n48_));
  NO3        g38(.A(new_n28_), .B(i7), .C(new_n16_), .Y(new_n49_));
  NO3        g39(.A(i8), .B(new_n13_), .C(new_n43_), .Y(new_n50_));
  OAI210     g40(.A0(new_n50_), .A1(new_n49_), .B0(new_n48_), .Y(new_n51_));
  NO2        g41(.A(new_n13_), .B(i2), .Y(new_n52_));
  NO2        g42(.A(i6), .B(new_n38_), .Y(new_n53_));
  OAI210     g43(.A0(i4), .A1(i1), .B0(i5), .Y(new_n54_));
  AOI220     g44(.A0(new_n54_), .A1(new_n53_), .B0(new_n52_), .B1(new_n20_), .Y(new_n55_));
  NA2        g45(.A(new_n55_), .B(new_n51_), .Y(new_n56_));
  OAI210     g46(.A0(new_n56_), .A1(new_n47_), .B0(i3), .Y(new_n57_));
  NAi31      g47(.An(i0), .B(i6), .C(i5), .Y(new_n58_));
  AOI210     g48(.A0(new_n58_), .A1(new_n28_), .B0(i1), .Y(new_n59_));
  OAI210     g49(.A0(new_n59_), .A1(new_n53_), .B0(new_n30_), .Y(new_n60_));
  NO3        g50(.A(new_n40_), .B(i7), .C(i4), .Y(new_n61_));
  NO3        g51(.A(new_n11_), .B(new_n16_), .C(i5), .Y(new_n62_));
  OAI210     g52(.A0(new_n62_), .A1(new_n61_), .B0(new_n38_), .Y(new_n63_));
  AOI210     g53(.A0(new_n63_), .A1(new_n60_), .B0(new_n43_), .Y(new_n64_));
  NOi21      g54(.An(i2), .B(i6), .Y(new_n65_));
  NOi31      g55(.An(i8), .B(i3), .C(i2), .Y(new_n66_));
  OAI210     g56(.A0(new_n66_), .A1(new_n65_), .B0(new_n38_), .Y(new_n67_));
  NOi31      g57(.An(i8), .B(i7), .C(i2), .Y(new_n68_));
  NOi21      g58(.An(i6), .B(i8), .Y(new_n69_));
  OAI210     g59(.A0(new_n69_), .A1(new_n68_), .B0(new_n13_), .Y(new_n70_));
  OR4        g60(.A(new_n31_), .B(i8), .C(new_n13_), .D(new_n30_), .Y(new_n71_));
  NA2        g61(.A(i7), .B(i6), .Y(new_n72_));
  NA3        g62(.A(new_n72_), .B(new_n31_), .C(new_n30_), .Y(new_n73_));
  AN4        g63(.A(new_n73_), .B(new_n71_), .C(new_n70_), .D(new_n67_), .Y(new_n74_));
  NO2        g64(.A(new_n74_), .B(new_n48_), .Y(new_n75_));
  NA2        g65(.A(new_n30_), .B(new_n43_), .Y(new_n76_));
  AOI210     g66(.A0(new_n16_), .A1(new_n43_), .B0(new_n11_), .Y(new_n77_));
  OA220      g67(.A0(new_n77_), .A1(new_n38_), .B0(new_n72_), .B1(new_n76_), .Y(new_n78_));
  NO3        g68(.A(new_n78_), .B(new_n40_), .C(i1), .Y(new_n79_));
  NO2        g69(.A(new_n11_), .B(new_n13_), .Y(new_n80_));
  OAI210     g70(.A0(i3), .A1(i1), .B0(i2), .Y(new_n81_));
  OAI210     g71(.A0(i5), .A1(i3), .B0(new_n31_), .Y(new_n82_));
  NO2        g72(.A(new_n40_), .B(new_n38_), .Y(new_n83_));
  AOI220     g73(.A0(new_n83_), .A1(new_n82_), .B0(new_n81_), .B1(new_n80_), .Y(new_n84_));
  INV        g74(.A(i1), .Y(new_n85_));
  AOI220     g75(.A0(new_n80_), .A1(new_n85_), .B0(new_n33_), .B1(i0), .Y(new_n86_));
  OAI220     g76(.A0(new_n86_), .A1(i8), .B0(new_n84_), .B1(i4), .Y(new_n87_));
  NO4        g77(.A(new_n87_), .B(new_n79_), .C(new_n75_), .D(new_n64_), .Y(new_n88_));
  NA3        g78(.A(new_n88_), .B(new_n57_), .C(new_n37_), .Y(zori0));
endmodule


