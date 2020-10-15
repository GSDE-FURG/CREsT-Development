// Benchmark "b01_C" written by ABC on Tue Aug 04 14:47:08 2020

module b01_C ( 
    LINE1, LINE2, OVERFLW_REG_SCAN_IN, STATO_REG_2__SCAN_IN,
    STATO_REG_1__SCAN_IN, STATO_REG_0__SCAN_IN, OUTP_REG_SCAN_IN,
    U45, U36, U35, U44, U34  );
  input  LINE1, LINE2, OVERFLW_REG_SCAN_IN, STATO_REG_2__SCAN_IN,
    STATO_REG_1__SCAN_IN, STATO_REG_0__SCAN_IN, OUTP_REG_SCAN_IN;
  output U45, U36, U35, U44, U34;
  wire n14, n15, n16, n17, n18, n20, n21, n22, n23, n24, n25, n26, n27, n28,
    n29, n30, n32, n33, n34, n35, n36, n37, n38, n40;
  NOR3X1  g00(.A(STATO_REG_0__SCAN_IN), .B(LINE2), .C(LINE1), .Y(n14));
  INVX1   g01(.A(STATO_REG_1__SCAN_IN), .Y(n15));
  NAND2X1 g02(.A(n15), .B(STATO_REG_2__SCAN_IN), .Y(n16));
  INVX1   g03(.A(STATO_REG_0__SCAN_IN), .Y(n17));
  AOI22X1 g04(.A0(STATO_REG_1__SCAN_IN), .A1(n17), .B0(LINE2), .B1(LINE1), .Y(n18));
  OAI22X1 g05(.A0(n16), .A1(n14), .B0(STATO_REG_2__SCAN_IN), .B1(n18), .Y(U45));
  NOR2X1  g06(.A(LINE2), .B(LINE1), .Y(n20));
  INVX1   g07(.A(STATO_REG_2__SCAN_IN), .Y(n21));
  AOI21X1 g08(.A0(n20), .A1(STATO_REG_1__SCAN_IN), .B0(n21), .Y(n22));
  OAI21X1 g09(.A0(n20), .A1(STATO_REG_0__SCAN_IN), .B0(n22), .Y(n23));
  INVX1   g10(.A(LINE1), .Y(n24));
  INVX1   g11(.A(LINE2), .Y(n25));
  NOR2X1  g12(.A(n25), .B(n24), .Y(n26));
  NOR2X1  g13(.A(STATO_REG_0__SCAN_IN), .B(n15), .Y(n27));
  OAI21X1 g14(.A0(n26), .A1(n21), .B0(n27), .Y(n28));
  NAND2X1 g15(.A(LINE2), .B(LINE1), .Y(n29));
  NAND3X1 g16(.A(n29), .B(STATO_REG_0__SCAN_IN), .C(n15), .Y(n30));
  NAND3X1 g17(.A(n30), .B(n28), .C(n23), .Y(U36));
  NAND3X1 g18(.A(n17), .B(LINE2), .C(LINE1), .Y(n32));
  NAND2X1 g19(.A(n29), .B(STATO_REG_0__SCAN_IN), .Y(n33));
  NAND4X1 g20(.A(n32), .B(n15), .C(n21), .D(n33), .Y(n34));
  NAND4X1 g21(.A(STATO_REG_0__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .C(n21), .D(n29), .Y(n35));
  NAND2X1 g22(.A(n25), .B(n24), .Y(n36));
  AOI21X1 g23(.A0(n17), .A1(STATO_REG_1__SCAN_IN), .B0(n21), .Y(n37));
  AOI22X1 g24(.A0(n27), .A1(n26), .B0(n36), .B1(n37), .Y(n38));
  NAND3X1 g25(.A(n38), .B(n35), .C(n34), .Y(U35));
  XOR2X1  g26(.A(LINE2), .B(LINE1), .Y(n40));
  XOR2X1  g27(.A(n40), .B(n37), .Y(U44));
  NOR3X1  g28(.A(n17), .B(n15), .C(STATO_REG_2__SCAN_IN), .Y(U34));
endmodule


