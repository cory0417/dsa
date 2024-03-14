package org.example

fun main() {
    val complexImg = importImage("data/paul.tiff")
    val fftResult = fft2D(complexImg)
    val fftShifted = fftShift(fftResult)
    val logF = applyLogTransform(fftShifted)
    val fileName = "data/result_mag.png"
    saveGrayscaleImage(logF, fileName)
//    fftResult[0].map { print("${it.abs()}, ") }

    val phaseData = extractPhase(fftResult)
    saveGrayscaleImage(phaseData, "data/result_phase.png")
}
