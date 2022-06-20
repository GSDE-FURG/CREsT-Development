// Benchmark "5xp1/5xp11_esp" written by ABC on Sun Oct 10 00:21:50 2021

module \5xp1/5xp11_esp  ( 
    i0, i1, i2, i3, i4, i5, i6,
    zori10, zori11, zori12, zori13, zori14, zori15, zori16, zori17, zori18,
    zori19  );
  input  i0, i1, i2, i3, i4, i5, i6;
  output zori10, zori11, zori12, zori13, zori14, zori15, zori16, zori17,
    zori18, zori19;
  wire new_n18_, new_n19_, new_n20_, new_n21_, new_n22_, new_n23_, new_n24_,
    new_n25_, new_n26_, new_n27_, new_n28_, new_n29_, new_n30_, new_n31_,
    new_n32_, new_n34_, new_n35_, new_n36_, new_n37_, new_n38_, new_n39_,
    new_n40_, new_n41_, new_n42_, new_n43_, new_n44_, new_n45_, new_n46_,
    new_n47_, new_n48_, new_n49_, new_n50_, new_n51_, new_n52_, new_n53_,
    new_n54_, new_n55_, new_n56_, new_n58_, new_n59_, new_n61_, new_n63_,
    new_n66_, new_n67_, new_n68_, new_n70_, new_n71_, new_n73_, new_n74_,
    new_n75_, new_n76_, new_n77_, new_n78_, new_n79_, new_n80_, new_n81_,
    new_n82_, new_n83_, new_n85_, new_n86_, new_n87_, new_n88_, new_n89_;
  INV        g00(.A(i1), .Y(new_n18_));
  INV        g01(.A(i3), .Y(new_n19_));
  NA2        g02(.A(new_n19_), .B(new_n18_), .Y(new_n20_));
  NO2        g03(.A(i4), .B(i1), .Y(new_n21_));
  NA2        g04(.A(i5), .B(i2), .Y(new_n22_));
  NO2        g05(.A(new_n22_), .B(new_n21_), .Y(new_n23_));
  INV        g06(.A(i6), .Y(new_n24_));
  OAI210     g07(.A0(i4), .A1(i3), .B0(i1), .Y(new_n25_));
  NA2        g08(.A(new_n25_), .B(new_n24_), .Y(new_n26_));
  OAI210     g09(.A0(new_n26_), .A1(new_n23_), .B0(new_n20_), .Y(new_n27_));
  NO2        g10(.A(i5), .B(i4), .Y(new_n28_));
  OAI210     g11(.A0(i5), .A1(i2), .B0(i4), .Y(new_n29_));
  OA220      g12(.A0(new_n29_), .A1(new_n19_), .B0(new_n28_), .B1(new_n18_), .Y(new_n30_));
  NA2        g13(.A(i3), .B(i1), .Y(new_n31_));
  OAI210     g14(.A0(new_n30_), .A1(new_n24_), .B0(new_n31_), .Y(new_n32_));
  MUX2       g15(.S(i0), .A(new_n32_), .B(new_n27_), .Y(zori10));
  INV        g16(.A(i0), .Y(new_n34_));
  OAI210     g17(.A0(new_n28_), .A1(new_n24_), .B0(new_n34_), .Y(new_n35_));
  INV        g18(.A(i4), .Y(new_n36_));
  AOI210     g19(.A0(i5), .A1(i2), .B0(i6), .Y(new_n37_));
  NA2        g20(.A(new_n37_), .B(new_n36_), .Y(new_n38_));
  AOI210     g21(.A0(new_n38_), .A1(new_n35_), .B0(new_n18_), .Y(new_n39_));
  NA2        g22(.A(i4), .B(i2), .Y(new_n40_));
  NA2        g23(.A(i6), .B(new_n18_), .Y(new_n41_));
  AOI210     g24(.A0(new_n40_), .A1(new_n34_), .B0(new_n41_), .Y(new_n42_));
  OAI210     g25(.A0(new_n42_), .A1(new_n39_), .B0(new_n19_), .Y(new_n43_));
  AOI210     g26(.A0(i4), .A1(i0), .B0(new_n18_), .Y(new_n44_));
  NA2        g27(.A(i2), .B(i0), .Y(new_n45_));
  NA2        g28(.A(i5), .B(i4), .Y(new_n46_));
  OAI210     g29(.A0(new_n46_), .A1(new_n45_), .B0(new_n24_), .Y(new_n47_));
  OR2        g30(.A(i4), .B(i0), .Y(new_n48_));
  AN2        g31(.A(i6), .B(i1), .Y(new_n49_));
  NO2        g32(.A(i1), .B(i0), .Y(new_n50_));
  AOI220     g33(.A0(new_n50_), .A1(new_n29_), .B0(new_n49_), .B1(new_n48_), .Y(new_n51_));
  OAI210     g34(.A0(new_n47_), .A1(new_n44_), .B0(new_n51_), .Y(new_n52_));
  OAI210     g35(.A0(new_n20_), .A1(new_n36_), .B0(new_n31_), .Y(new_n53_));
  INV        g36(.A(i5), .Y(new_n54_));
  AOI210     g37(.A0(new_n45_), .A1(new_n24_), .B0(new_n54_), .Y(new_n55_));
  AOI220     g38(.A0(new_n55_), .A1(new_n53_), .B0(new_n52_), .B1(i3), .Y(new_n56_));
  NA2        g39(.A(new_n56_), .B(new_n43_), .Y(zori11));
  NO2        g40(.A(i6), .B(i1), .Y(new_n58_));
  OAI210     g41(.A0(new_n40_), .A1(new_n54_), .B0(new_n58_), .Y(new_n59_));
  NOi31      g42(.An(new_n59_), .B(new_n19_), .C(new_n34_), .Y(zori13));
  NA3        g43(.A(new_n38_), .B(i1), .C(i0), .Y(new_n61_));
  AOI210     g44(.A0(new_n61_), .A1(new_n19_), .B0(zori13), .Y(zori12));
  NO2        g45(.A(i5), .B(i2), .Y(new_n63_));
  NOi21      g46(.An(new_n22_), .B(new_n63_), .Y(zori14));
  INV        g47(.A(i2), .Y(zori15));
  NA2        g48(.A(new_n28_), .B(i2), .Y(new_n66_));
  XO2        g49(.A(zori14), .B(i6), .Y(new_n67_));
  NA2        g50(.A(new_n67_), .B(new_n66_), .Y(new_n68_));
  OAI210     g51(.A0(new_n66_), .A1(new_n24_), .B0(new_n68_), .Y(zori16));
  NA2        g52(.A(new_n54_), .B(i2), .Y(new_n70_));
  NA2        g53(.A(new_n70_), .B(i4), .Y(new_n71_));
  NA2        g54(.A(new_n71_), .B(new_n66_), .Y(zori17));
  NO2        g55(.A(i6), .B(new_n34_), .Y(new_n73_));
  NO2        g56(.A(new_n24_), .B(i0), .Y(new_n74_));
  AOI220     g57(.A0(new_n74_), .A1(new_n29_), .B0(new_n73_), .B1(new_n40_), .Y(new_n75_));
  NO2        g58(.A(new_n75_), .B(i1), .Y(new_n76_));
  AOI220     g59(.A0(new_n74_), .A1(new_n54_), .B0(new_n37_), .B1(i0), .Y(new_n77_));
  NO2        g60(.A(new_n77_), .B(i4), .Y(new_n78_));
  NO3        g61(.A(i5), .B(zori15), .C(i1), .Y(new_n79_));
  NO3        g62(.A(new_n79_), .B(new_n49_), .C(new_n34_), .Y(new_n80_));
  NO4        g63(.A(new_n80_), .B(new_n74_), .C(new_n50_), .D(new_n36_), .Y(new_n81_));
  OAI210     g64(.A0(i6), .A1(i2), .B0(i5), .Y(new_n82_));
  NO4        g65(.A(new_n82_), .B(new_n74_), .C(new_n73_), .D(new_n21_), .Y(new_n83_));
  OR4        g66(.A(new_n83_), .B(new_n81_), .C(new_n78_), .D(new_n76_), .Y(zori18));
  NA2        g67(.A(new_n82_), .B(new_n36_), .Y(new_n85_));
  AOI210     g68(.A0(new_n70_), .A1(new_n46_), .B0(new_n37_), .Y(new_n86_));
  NOi21      g69(.An(new_n85_), .B(new_n86_), .Y(new_n87_));
  NO2        g70(.A(new_n37_), .B(new_n29_), .Y(new_n88_));
  NA2        g71(.A(new_n85_), .B(new_n18_), .Y(new_n89_));
  OAI220     g72(.A0(new_n89_), .A1(new_n88_), .B0(new_n87_), .B1(new_n18_), .Y(zori19));
endmodule


