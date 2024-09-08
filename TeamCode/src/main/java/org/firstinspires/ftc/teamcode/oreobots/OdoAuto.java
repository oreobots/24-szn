package org.firstinspires.ftc.teamcode.auto;
import android.util.Size;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gyroscope;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import org.firstinspires.ftc.robotcore.external.JavaUtil;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name="RoboTestAuto")
public class RoboTestAuto extends LinearOpMode {


  private DcMotor frontRight;
  private DcMotor frontLeft;
  private DcMotor rearRight;
  private DcMotor rearLeft;
 
    
 @Override
  public void runOpMode() {
        frontRight = hardwareMap.get(DcMotor.class, "frontRight");
        frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
        rearRight = hardwareMap.get(DcMotor.class, "rearRight");
        rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
            waitForStart();
    if (opModeIsActive()) {
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        rearRight.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
          while (opModeIsActive()) {
              forward(500,0.3);
              sleep(100);
              backward(500, 0.3);
              sleep(100);
              right(500, 0.3);
              sleep(100);
              left(500, 0.3);
              sleep(100);

        
        telemetry.update();
      }
    }
  }  
  public void backward(int runTime, double power) {
        strafe(0, 1, 0, runTime, power);
    
    }
    
    public void forward(int runTime, double power) {
        strafe(0, -1, 0, runTime, power);
    }
    
    public void left(int runTime, double power) {
        strafe(1, 0, 0, runTime, power);
    }
    
    public void right(int runTime, double power) {
        strafe(-1, 0, 0, runTime, power);
    }
    
    public void rotateLeft(int runTime, double power) {
        strafe(0, 0, -1, runTime, power);
    }
    
    public void rotateRight(int runTime, double power) {
        strafe(0, 0, 1, runTime, power);
    }
          public void strafe(int X, int Y, int RX, int runTime, double P){
        if (!opModeIsActive()) return;
        double denominator = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(Y), Math.abs(X), Math.abs(RX))), 1));
        double FrontLeftPower = (Y + X + RX) / denominator;
        double RearLeftPower = ((Y - X) + RX) / denominator;
        double FrontRightPower = ((Y - X) - RX) / denominator;
        double RearRightPower = ((Y + X) - RX) / denominator;
        
        frontLeft.setPower(fabsolute(FrontLeftPower, P));
        rearLeft.setPower(fabsolute(RearLeftPower, P));
        frontRight.setPower(fabsolute(FrontRightPower, P));
        rearRight.setPower(fabsolute(RearRightPower, P));
        
        sleep(runTime);
        
        frontLeft.setPower(0);
        rearLeft.setPower(0);
        frontRight.setPower(0);
        rearRight.setPower(0);
    }

    private double fabsolute(double value, double f_power) {
        double fabs_value;

        if (value > 0) {
            fabs_value = JavaUtil.minOfList(JavaUtil.createListWith(value, f_power));
        } else if (value < 0) {
            fabs_value = JavaUtil.maxOfList(JavaUtil.createListWith(value, -f_power));
        } else {
            fabs_value = value;
        }
        return fabs_value;
    }
            }
        
      

    
   
    
    
   /* public void dropBucket(double position) {
        double currentPosition = 0.32;
        while (opModeIsActive() && currentPosition < 1) {
            bucketServo.setPosition(currentPosition);
            sleep(10);
            currentPosition += 0.004;
        }
        
    }*/

   
    

