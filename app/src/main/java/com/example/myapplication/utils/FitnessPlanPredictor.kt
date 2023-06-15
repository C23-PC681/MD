package com.example.myapplication.utils

import android.content.Context
import org.tensorflow.lite.Interpreter
import java.io.FileInputStream
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.channels.FileChannel

class FitnessPlanPredictor(private val context: Context) {

    private val modelPath = "model/classification.tflite"
    private lateinit var interpreter: Interpreter

    fun initializeInterpreter() {
        // Load the Random Forest model from the assets directory
        val modelFile = context.assets.openFd(modelPath)
        val modelInputStream = FileInputStream(modelFile.fileDescriptor)
        val modelBuffer = modelInputStream.channel.map(
            FileChannel.MapMode.READ_ONLY,
            modelFile.startOffset,
            modelFile.declaredLength
        )

        // Create the TensorFlow Lite interpreter
        interpreter = Interpreter(modelBuffer)
    }

    fun predictFitnessPlan(height: Float, weight: Float, gender: Int, goal: Int): Int {
        // Prepare the input data
        val inputBuffer = ByteBuffer.allocateDirect(20) // Assuming 4 features x 4 bytes per float
        inputBuffer.order(ByteOrder.nativeOrder())
        inputBuffer.putFloat(25F)
        inputBuffer.putFloat(weight)
        inputBuffer.putFloat(height)
        inputBuffer.putFloat(gender.toFloat())
        inputBuffer.putFloat(goal.toFloat())
        inputBuffer.flip()

        // Prepare the output buffer
        val outputBuffer = ByteBuffer.allocateDirect(4) // Assuming output size is 4 bytes
        outputBuffer.order(ByteOrder.nativeOrder())

        // Run inference
        interpreter.run(inputBuffer, outputBuffer)

        // Process the output
        outputBuffer.flip()
        val predictedClassIndex = outputBuffer.int // Assuming the output is an integer class index

        // Map the predicted class index to a fitness plan
//        val fitnessPlans =
//            listOf("Plan A", "Plan B", "Plan C",) // Replace with your fitness plans

//        return fitnessPlans[predictedClassIndex]
        return predictedClassIndex
    }
}