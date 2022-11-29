JC = javac

all: Main

Main:
	$(JC) Main.java State.java Rectangle.java

clean: CMain

CMain :
	rm -rf *.class

CDMain:
	rm -rf *.class
