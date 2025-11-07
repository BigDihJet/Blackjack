/**
 * Die Spielfigur der Blumenwelt.
 * 
 * @author (Peter Brichzin) 
 * @version (Version 1.0)
 */
class Florian extends Figur
{

    /**
     * Legt die Form und die Startposition fest.
     */
    Florian()
    {
        super();
        this.FigurteilFestlegenRechteck(-20, -15, 40, 60, "orange");
        this.FigurteilFestlegenEllipse(-10, -50, 20, 30, "orange");
        this.FigurteilFestlegenDreieck(0, 0, 0, 30, 18, 15, "gelb");
        this.PositionSetzen(75, 125);
        this.GanzNachVornBringen();
    }

    /**
     * Lässt Florian eine Zelle nach Osten gehen.
     */
    void NachOstenGehen()
    {
        this.PositionSetzen(this.XPositionGeben()+50, this.YPositionGeben());
    }

    /**
     * Dreht sich um 90 Grad nach links
     * Tipp: Teste vorab (an einem Objekt der Klasse Florian) 
     * die Methoden Drehen, WinkelGeben, WinkelSetzen der Oberklasse
     */
    void LinksDrehen()
    {
        this.WinkelSetzen(this.WinkelGeben()-90);
    }

    

}
