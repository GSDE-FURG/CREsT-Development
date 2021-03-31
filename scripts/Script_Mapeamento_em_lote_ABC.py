import glob
import os

def readFolder():
    pritn("EEEE")

def mapCircuit(nomeCircuit, genlib):
    nameCircuit = nomeCircuit.replace(".blif", "")
    nameGenlib = genlib.replace(".genlib", "")
    print("Circuito: " + nomeCircuit  + "   -> " + nameCircuit + " -> " + nameGenlib )
    print("     Genlib: " + genlib)
    os.system("./abc -c 'read_blif " + str(nomeCircuit)+ "'" + " -c 'read_genlib " + genlib + "'" + " '-c map'" + " '-c write_verilog " + nameCircuit + "_" + nameGenlib  + ".v'")


circuitList = []
genlibraryList = []


for file in glob.glob("*.blif"):
    circuitList.append(file)

for genlib in glob.glob("*.genlib"):
    genlibraryList.append(genlib)

for lib in genlibraryList:
    for i in circuitList:
        mapCircuit(i, lib)
    print("-----------")
