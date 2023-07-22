// Benchmark "9sym/9sym_1_18_0_esp" written by ABC on Wed Mar  8 02:20:16 2023

module \9sym/9sym_1_18_0_esp  ( 
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
    new_n95_, new_n96_, new_n97_, new_n98_;
  INV        g00(.A(i1), .Y(new_n11_));
  INV        g01(.A(i8), .Y(new_n12_));
  INV        g02(.A(i4), .Y(new_n13_));
  NO2        g03(.A(new_n13_), .B(i3), .Y(new_n14_));
  INV        g04(.A(i3), .Y(new_n15_));
  AOI210     g05(.A0(new_n12_), .A1(new_n15_), .B0(i2), .Y(new_n16_));
  OAI210     g06(.A0(new_n14_), .A1(new_n12_), .B0(new_n16_), .Y(new_n17_));
  NO2        g07(.A(i8), .B(i7), .Y(new_n18_));
  INV        g08(.A(i2), .Y(new_n19_));
  NAi21      g09(.An(i8), .B(i4), .Y(new_n20_));
  OAI210     g10(.A0(i4), .A1(new_n19_), .B0(new_n20_), .Y(new_n21_));
  INV        g11(.A(i5), .Y(new_n22_));
  NO2        g12(.A(new_n22_), .B(i3), .Y(new_n23_));
  AOI220     g13(.A0(new_n23_), .A1(new_n21_), .B0(new_n18_), .B1(i6), .Y(new_n24_));
  AOI210     g14(.A0(new_n24_), .A1(new_n17_), .B0(new_n11_), .Y(new_n25_));
  INV        g15(.A(i6), .Y(new_n26_));
  NO2        g16(.A(new_n26_), .B(i2), .Y(new_n27_));
  NOi21      g17(.An(i8), .B(i7), .Y(new_n28_));
  OAI210     g18(.A0(new_n12_), .A1(i2), .B0(i3), .Y(new_n29_));
  NA2        g19(.A(i6), .B(i2), .Y(new_n30_));
  AOI210     g20(.A0(new_n30_), .A1(new_n15_), .B0(i1), .Y(new_n31_));
  AOI220     g21(.A0(new_n31_), .A1(new_n29_), .B0(new_n28_), .B1(new_n27_), .Y(new_n32_));
  NO2        g22(.A(new_n32_), .B(new_n22_), .Y(new_n33_));
  AO210      g23(.A0(i7), .A1(i1), .B0(new_n18_), .Y(new_n34_));
  NO3        g24(.A(new_n34_), .B(i4), .C(new_n19_), .Y(new_n35_));
  OAI210     g25(.A0(new_n28_), .A1(i3), .B0(new_n35_), .Y(new_n36_));
  NO2        g26(.A(new_n12_), .B(new_n22_), .Y(new_n37_));
  AO220      g27(.A0(new_n37_), .A1(i1), .B0(i4), .B1(i2), .Y(new_n38_));
  INV        g28(.A(i7), .Y(new_n39_));
  OAI220     g29(.A0(new_n30_), .A1(new_n39_), .B0(new_n15_), .B1(new_n11_), .Y(new_n40_));
  AOI220     g30(.A0(new_n40_), .A1(new_n22_), .B0(new_n38_), .B1(new_n26_), .Y(new_n41_));
  NA2        g31(.A(new_n41_), .B(new_n36_), .Y(new_n42_));
  NO3        g32(.A(new_n42_), .B(new_n33_), .C(new_n25_), .Y(new_n43_));
  INV        g33(.A(i0), .Y(new_n44_));
  NOi21      g34(.An(i4), .B(i5), .Y(new_n45_));
  NOi21      g35(.An(i5), .B(i7), .Y(new_n46_));
  OAI210     g36(.A0(new_n46_), .A1(new_n45_), .B0(i3), .Y(new_n47_));
  NA2        g37(.A(i8), .B(i7), .Y(new_n48_));
  MUX2       g38(.S(i1), .A(new_n48_), .B(new_n20_), .Y(new_n49_));
  AOI210     g39(.A0(new_n49_), .A1(new_n47_), .B0(i6), .Y(new_n50_));
  NO4        g40(.A(new_n37_), .B(new_n18_), .C(i3), .D(new_n11_), .Y(new_n51_));
  OAI210     g41(.A0(new_n51_), .A1(new_n50_), .B0(new_n19_), .Y(new_n52_));
  NA2        g42(.A(new_n26_), .B(i3), .Y(new_n53_));
  OAI210     g43(.A0(i5), .A1(i3), .B0(i8), .Y(new_n54_));
  NOi21      g44(.An(i5), .B(i2), .Y(new_n55_));
  AOI220     g45(.A0(new_n55_), .A1(i1), .B0(new_n54_), .B1(i6), .Y(new_n56_));
  OAI210     g46(.A0(new_n53_), .A1(new_n34_), .B0(new_n56_), .Y(new_n57_));
  NO3        g47(.A(i6), .B(i5), .C(i1), .Y(new_n58_));
  NO2        g48(.A(i7), .B(i3), .Y(new_n59_));
  OAI210     g49(.A0(new_n59_), .A1(new_n58_), .B0(i2), .Y(new_n60_));
  NA3        g50(.A(new_n45_), .B(new_n28_), .C(new_n26_), .Y(new_n61_));
  NOi21      g51(.An(i4), .B(i8), .Y(new_n62_));
  NOi21      g52(.An(i5), .B(i1), .Y(new_n63_));
  OAI210     g53(.A0(new_n28_), .A1(new_n62_), .B0(new_n63_), .Y(new_n64_));
  NA3        g54(.A(new_n64_), .B(new_n61_), .C(new_n60_), .Y(new_n65_));
  AOI210     g55(.A0(new_n57_), .A1(new_n13_), .B0(new_n65_), .Y(new_n66_));
  AOI210     g56(.A0(new_n66_), .A1(new_n52_), .B0(new_n44_), .Y(new_n67_));
  AOI210     g57(.A0(new_n12_), .A1(i6), .B0(new_n45_), .Y(new_n68_));
  NO2        g58(.A(i8), .B(i5), .Y(new_n69_));
  NO3        g59(.A(new_n69_), .B(new_n68_), .C(i2), .Y(new_n70_));
  NO3        g60(.A(new_n20_), .B(i6), .C(new_n22_), .Y(new_n71_));
  OAI210     g61(.A0(new_n71_), .A1(new_n70_), .B0(i3), .Y(new_n72_));
  OAI210     g62(.A0(new_n37_), .A1(i6), .B0(new_n14_), .Y(new_n73_));
  AOI210     g63(.A0(new_n73_), .A1(new_n72_), .B0(i7), .Y(new_n74_));
  NO2        g64(.A(i6), .B(i3), .Y(new_n75_));
  NOi21      g65(.An(new_n75_), .B(new_n39_), .Y(new_n76_));
  AOI220     g66(.A0(new_n76_), .A1(i2), .B0(new_n69_), .B1(i3), .Y(new_n77_));
  AOI210     g67(.A0(new_n69_), .A1(i6), .B0(new_n76_), .Y(new_n78_));
  OAI220     g68(.A0(new_n78_), .A1(new_n13_), .B0(new_n77_), .B1(new_n11_), .Y(new_n79_));
  OR2        g69(.A(i5), .B(i3), .Y(new_n80_));
  NA2        g70(.A(i6), .B(i3), .Y(new_n81_));
  OAI220     g71(.A0(new_n81_), .A1(i7), .B0(new_n80_), .B1(new_n12_), .Y(new_n82_));
  NA2        g72(.A(i5), .B(i3), .Y(new_n83_));
  NA3        g73(.A(new_n83_), .B(i8), .C(new_n19_), .Y(new_n84_));
  OAI210     g74(.A0(new_n84_), .A1(new_n75_), .B0(new_n20_), .Y(new_n85_));
  AOI220     g75(.A0(new_n85_), .A1(i7), .B0(new_n82_), .B1(i2), .Y(new_n86_));
  NA2        g76(.A(i5), .B(new_n19_), .Y(new_n87_));
  NO2        g77(.A(i6), .B(i5), .Y(new_n88_));
  NAi41      g78(.An(new_n88_), .B(new_n83_), .C(new_n80_), .D(new_n11_), .Y(new_n89_));
  AOI210     g79(.A0(new_n89_), .A1(new_n87_), .B0(new_n39_), .Y(new_n90_));
  NOi21      g80(.An(new_n88_), .B(new_n48_), .Y(new_n91_));
  OA210      g81(.A0(new_n91_), .A1(new_n27_), .B0(i1), .Y(new_n92_));
  NA3        g82(.A(new_n28_), .B(i6), .C(new_n22_), .Y(new_n93_));
  NA3        g83(.A(new_n12_), .B(i5), .C(i2), .Y(new_n94_));
  AOI210     g84(.A0(new_n94_), .A1(new_n93_), .B0(new_n15_), .Y(new_n95_));
  NO3        g85(.A(new_n95_), .B(new_n92_), .C(new_n90_), .Y(new_n96_));
  OAI220     g86(.A0(new_n96_), .A1(i4), .B0(new_n86_), .B1(i1), .Y(new_n97_));
  NO4        g87(.A(new_n97_), .B(new_n79_), .C(new_n74_), .D(new_n67_), .Y(new_n98_));
  OAI210     g88(.A0(new_n43_), .A1(i0), .B0(new_n98_), .Y(zori0));
endmodule


