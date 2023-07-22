// Benchmark "9sym/9sym_1_22_0_esp" written by ABC on Wed Mar  8 02:21:24 2023

module \9sym/9sym_1_22_0_esp  ( 
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
    new_n88_, new_n89_, new_n90_, new_n91_, new_n92_;
  INV        g00(.A(i6), .Y(new_n11_));
  INV        g01(.A(i0), .Y(new_n12_));
  OA210      g02(.A0(i4), .A1(i1), .B0(i5), .Y(new_n13_));
  NO2        g03(.A(new_n13_), .B(new_n12_), .Y(new_n14_));
  NAi21      g04(.An(i2), .B(i1), .Y(new_n15_));
  NAi31      g05(.An(i8), .B(i5), .C(i4), .Y(new_n16_));
  AOI210     g06(.A0(new_n16_), .A1(new_n15_), .B0(i7), .Y(new_n17_));
  OAI210     g07(.A0(new_n17_), .A1(new_n14_), .B0(new_n11_), .Y(new_n18_));
  INV        g08(.A(i1), .Y(new_n19_));
  NAi31      g09(.An(i4), .B(i7), .C(i2), .Y(new_n20_));
  NAi31      g10(.An(i2), .B(i8), .C(i5), .Y(new_n21_));
  NA3        g11(.A(new_n21_), .B(new_n20_), .C(new_n19_), .Y(new_n22_));
  OR2        g12(.A(i8), .B(i2), .Y(new_n23_));
  NA3        g13(.A(new_n23_), .B(i5), .C(i1), .Y(new_n24_));
  NA3        g14(.A(new_n24_), .B(new_n22_), .C(new_n12_), .Y(new_n25_));
  INV        g15(.A(i2), .Y(new_n26_));
  NAi21      g16(.An(i7), .B(i6), .Y(new_n27_));
  NAi31      g17(.An(i2), .B(i8), .C(i7), .Y(new_n28_));
  OAI220     g18(.A0(new_n28_), .A1(i5), .B0(new_n27_), .B1(new_n26_), .Y(new_n29_));
  NA2        g19(.A(new_n29_), .B(new_n19_), .Y(new_n30_));
  NOi21      g20(.An(i5), .B(i8), .Y(new_n31_));
  NAi21      g21(.An(i2), .B(i6), .Y(new_n32_));
  OAI220     g22(.A0(new_n32_), .A1(i7), .B0(i4), .B1(new_n26_), .Y(new_n33_));
  OR2        g23(.A(i7), .B(i5), .Y(new_n34_));
  NA2        g24(.A(new_n34_), .B(i2), .Y(new_n35_));
  INV        g25(.A(i7), .Y(new_n36_));
  INV        g26(.A(i8), .Y(new_n37_));
  NAi21      g27(.An(i4), .B(i6), .Y(new_n38_));
  AOI210     g28(.A0(new_n37_), .A1(new_n36_), .B0(new_n38_), .Y(new_n39_));
  AOI220     g29(.A0(new_n39_), .A1(new_n35_), .B0(new_n33_), .B1(new_n31_), .Y(new_n40_));
  NA4        g30(.A(new_n40_), .B(new_n30_), .C(new_n25_), .D(new_n18_), .Y(new_n41_));
  NA2        g31(.A(new_n41_), .B(i3), .Y(new_n42_));
  AOI210     g32(.A0(new_n38_), .A1(new_n28_), .B0(i5), .Y(new_n43_));
  NOi21      g33(.An(i2), .B(i4), .Y(new_n44_));
  AOI210     g34(.A0(new_n37_), .A1(i4), .B0(new_n44_), .Y(new_n45_));
  NA2        g35(.A(i5), .B(new_n12_), .Y(new_n46_));
  OAI210     g36(.A0(i8), .A1(i2), .B0(i7), .Y(new_n47_));
  NA3        g37(.A(new_n47_), .B(new_n34_), .C(i0), .Y(new_n48_));
  OAI210     g38(.A0(new_n46_), .A1(new_n45_), .B0(new_n48_), .Y(new_n49_));
  OAI210     g39(.A0(new_n49_), .A1(new_n43_), .B0(i1), .Y(new_n50_));
  INV        g40(.A(i4), .Y(new_n51_));
  NO2        g41(.A(i5), .B(i1), .Y(new_n52_));
  NA2        g42(.A(new_n52_), .B(i8), .Y(new_n53_));
  AOI210     g43(.A0(new_n53_), .A1(new_n27_), .B0(new_n51_), .Y(new_n54_));
  AOI220     g44(.A0(i7), .A1(i4), .B0(i2), .B1(i0), .Y(new_n55_));
  NO2        g45(.A(new_n55_), .B(i6), .Y(new_n56_));
  NA2        g46(.A(i5), .B(new_n51_), .Y(new_n57_));
  OR2        g47(.A(new_n32_), .B(new_n37_), .Y(new_n58_));
  NA2        g48(.A(i7), .B(new_n19_), .Y(new_n59_));
  AOI210     g49(.A0(new_n58_), .A1(new_n57_), .B0(new_n59_), .Y(new_n60_));
  NAi21      g50(.An(i1), .B(i5), .Y(new_n61_));
  NO3        g51(.A(new_n61_), .B(new_n11_), .C(new_n26_), .Y(new_n62_));
  NAi21      g52(.An(i2), .B(i4), .Y(new_n63_));
  NO2        g53(.A(new_n63_), .B(new_n37_), .Y(new_n64_));
  OA210      g54(.A0(new_n64_), .A1(new_n62_), .B0(new_n12_), .Y(new_n65_));
  NO4        g55(.A(new_n65_), .B(new_n60_), .C(new_n56_), .D(new_n54_), .Y(new_n66_));
  AO210      g56(.A0(new_n66_), .A1(new_n50_), .B0(i3), .Y(new_n67_));
  AOI220     g57(.A0(i8), .A1(i5), .B0(i7), .B1(i2), .Y(new_n68_));
  OAI220     g58(.A0(new_n68_), .A1(new_n19_), .B0(new_n51_), .B1(new_n26_), .Y(new_n69_));
  NA2        g59(.A(new_n69_), .B(new_n11_), .Y(new_n70_));
  NOi21      g60(.An(i6), .B(i5), .Y(new_n71_));
  NO2        g61(.A(i7), .B(i4), .Y(new_n72_));
  AOI220     g62(.A0(new_n72_), .A1(i8), .B0(new_n71_), .B1(i7), .Y(new_n73_));
  OA220      g63(.A0(new_n73_), .A1(new_n26_), .B0(new_n27_), .B1(new_n21_), .Y(new_n74_));
  AOI210     g64(.A0(new_n74_), .A1(new_n70_), .B0(i0), .Y(new_n75_));
  NAi21      g65(.An(i6), .B(i0), .Y(new_n76_));
  NO2        g66(.A(new_n76_), .B(i1), .Y(new_n77_));
  NO2        g67(.A(new_n34_), .B(new_n51_), .Y(new_n78_));
  OAI210     g68(.A0(new_n78_), .A1(new_n77_), .B0(new_n26_), .Y(new_n79_));
  OAI210     g69(.A0(i6), .A1(i4), .B0(i1), .Y(new_n80_));
  NO2        g70(.A(i7), .B(new_n12_), .Y(new_n81_));
  AOI220     g71(.A0(new_n81_), .A1(new_n80_), .B0(new_n52_), .B1(new_n44_), .Y(new_n82_));
  AOI210     g72(.A0(new_n82_), .A1(new_n79_), .B0(new_n37_), .Y(new_n83_));
  OAI220     g73(.A0(new_n76_), .A1(new_n63_), .B0(new_n27_), .B1(i0), .Y(new_n84_));
  NA2        g74(.A(new_n84_), .B(i1), .Y(new_n85_));
  AO210      g75(.A0(i7), .A1(new_n19_), .B0(new_n71_), .Y(new_n86_));
  OAI210     g76(.A0(i6), .A1(i4), .B0(i0), .Y(new_n87_));
  AOI210     g77(.A0(new_n61_), .A1(i4), .B0(new_n87_), .Y(new_n88_));
  AOI210     g78(.A0(new_n86_), .A1(i4), .B0(new_n88_), .Y(new_n89_));
  AOI210     g79(.A0(new_n89_), .A1(new_n85_), .B0(i8), .Y(new_n90_));
  NO3        g80(.A(new_n57_), .B(new_n36_), .C(i2), .Y(new_n91_));
  NO4        g81(.A(new_n91_), .B(new_n90_), .C(new_n83_), .D(new_n75_), .Y(new_n92_));
  NA3        g82(.A(new_n92_), .B(new_n67_), .C(new_n42_), .Y(zori0));
endmodule


