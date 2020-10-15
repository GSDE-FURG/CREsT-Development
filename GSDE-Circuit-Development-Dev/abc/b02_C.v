// Benchmark "b02_C" written by ABC on Wed Aug 05 14:32:52 2020

module b02_C ( 
    U_REG_SCAN_IN, LINEA, STATO_REG_2__SCAN_IN, STATO_REG_1__SCAN_IN,
    STATO_REG_0__SCAN_IN,
    U33, U38, U32, U31  );
  input  U_REG_SCAN_IN, LINEA, STATO_REG_2__SCAN_IN,
    STATO_REG_1__SCAN_IN, STATO_REG_0__SCAN_IN;
  output U33, U38, U32, U31;
  wire n10, n11, n12, n13, n14, n15, n17, n18, n19, n21, n22;
  INVX1   g00(.A(STATO_REG_1__SCAN_IN), .Y(n10));
  INVX1   g01(.A(LINEA), .Y(n11));
  INVX1   g02(.A(STATO_REG_0__SCAN_IN), .Y(n12));
  OAI21X1 g03(.A0(STATO_REG_2__SCAN_IN), .A1(n11), .B0(n12), .Y(n13));
  AOI21X1 g04(.A0(STATO_REG_2__SCAN_IN), .A1(n11), .B0(n13), .Y(n14));
  OAI21X1 g05(.A0(STATO_REG_2__SCAN_IN), .A1(LINEA), .B0(STATO_REG_0__SCAN_IN), .Y(n15));
  OAI21X1 g06(.A0(n14), .A1(n10), .B0(n15), .Y(U33));
  INVX1   g07(.A(STATO_REG_2__SCAN_IN), .Y(n17));
  NAND3X1 g08(.A(n12), .B(STATO_REG_1__SCAN_IN), .C(n17), .Y(n18));
  AOI21X1 g09(.A0(n10), .A1(n11), .B0(STATO_REG_2__SCAN_IN), .Y(n19));
  OAI21X1 g10(.A0(n19), .A1(n12), .B0(n18), .Y(U38));
  AOI21X1 g11(.A0(n17), .A1(LINEA), .B0(n12), .Y(n21));
  NAND3X1 g12(.A(n12), .B(n17), .C(n11), .Y(n22));
  OAI21X1 g13(.A0(n21), .A1(STATO_REG_1__SCAN_IN), .B0(n22), .Y(U32));
  NOR3X1  g14(.A(STATO_REG_0__SCAN_IN), .B(STATO_REG_1__SCAN_IN), .C(n17), .Y(U31));
endmodule


