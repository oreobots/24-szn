package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.JavaUtil;

@TeleOp(name = "RoboTest")
public class OdoTeleop extends LinearOpMode {


  private DcMotor frontRight;
  private DcMotor frontLeft;
  private DcMotor rearRight;
  private DcMotor rearLeft;
  
  /**
   * This function is executed when this Op Mode is selected from the Driver Station.
   */
  @Override
  public void runOpMode() {
    int tgtPosition;
    ElapsedTime speedChangeTime;
    double sensitivity;
    double MaxPower;
    float Y;
    float X;
    double RX;
    double DENOMINATOR;
    double FrontLeftPower;
    double RearLeftPower;
    double FrontRightPower;
    double RearRightPower;
   


    frontRight = hardwareMap.get(DcMotor.class, "frontRight");
    frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
    rearRight = hardwareMap.get(DcMotor.class, "rearRight");
    rearLeft = hardwareMap.get(DcMotor.class, "rearLeft");
   
    // Put initialization blocks here.
    waitForStart();
    speedChangeTime = new ElapsedTime();
    sensitivity = 1;
    MaxPower = 0.7;
    tgtPosition = 3;
    if (opModeIsActive()) {
      while (opModeIsActive()) {
        // Put loop blocks here.
        frontRight.setDirection(DcMotor.Direction.FORWARD);
        frontLeft.setDirection(DcMotor.Direction.REVERSE);
        rearRight.setDirection(DcMotor.Direction.FORWARD);
        rearLeft.setDirection(DcMotor.Direction.REVERSE);
        telemetry.update();
        Y = -gamepad1.left_stick_y;
        X = gamepad1.right_stick_x;
        RX = gamepad1.left_stick_x * 1.1;
        DENOMINATOR = JavaUtil.maxOfList(JavaUtil.createListWith(JavaUtil.sumOfList(JavaUtil.createListWith(Math.abs(Y), Math.abs(X), Math.abs(RX))), 1));
        FrontLeftPower = (Y + X + RX) / DENOMINATOR;
        RearLeftPower = ((Y - X) + RX) / DENOMINATOR;
        FrontRightPower = ((Y - X) - RX) / DENOMINATOR;
        RearRightPower = ((Y + X) - RX) / DENOMINATOR;
        frontLeft.setPower(fabsolute(FrontLeftPower, MaxPower));
        rearLeft.setPower(fabsolute(RearLeftPower, MaxPower));
        frontRight.setPower(fabsolute(FrontRightPower, MaxPower));
        rearRight.setPower(fabsolute(RearRightPower, MaxPower));
        
        /*if (gamepad1.){
        frontLeft.setPower(fabsolute(FrontLeftPower, MaxPower*0.4));
        rearLeft.setPower(fabsolute(RearLeftPower, MaxPower*0.4));
        frontRight.setPower(fabsolute(FrontRightPower, MaxPower*0.4));
        rearRight.setPower(fabsolute(RearRightPower, MaxPower*0.4));
        }
        else {
          frontLeft.setPower(fabsolute(FrontLeftPower, MaxPower));
          rearLeft.setPower(fabsolute(RearLeftPower, MaxPower));
          frontRight.setPower(fabsolute(FrontRightPower, MaxPower));
          rearRight.setPower(fabsolute(RearRightPower, MaxPower));
        }*/
        //Left bumper controls speed in steps 0.3, 0.6, 0.9
        if (gamepad1.left_bumper && speedChangeTime.milliseconds() > 500) {
          if (MaxPower == 0.4) {
            MaxPower = 0.7;
          } /*else if (MaxPower == 0.6) {
           MaxPower = 1;
          }*/ else {
           MaxPower = 0.4;
          }
          speedChangeTime.reset();
          //telemetry.speak(MaxPower, null, null);
        } 
       
        
          

        //encoder test code
        /*
        if(gamepad2.dpad_left) {
          elevatorEncoder(200,0.4);
          sleep(500);
          elevatorEncoder(0,0.4);
        }
        */
       
        
        /*if (gamepad1.y || gamepad2.y) {
          ServoPosition = 0.6;
          while (gamepad1.y || gamepad2.y) {
            ServoPosition = 1.0;
            clawServo.setPosition(ServoPosition);
            sleep(10);
            ServoPosition += 0.008;
            telemetry.addData("ServoPosition", ServoPosition);
            telemetry.update();
          }
        } else {
         clawServo.setPosition(0.6);
        }*/
     
        telemetry.addData("Front Left Power", frontLeft.getPower());
        telemetry.addData("Rear Left Power", rearLeft.getPower());
        telemetry.addData("Front Right Power", frontRight.getPower());
        telemetry.addData("Rear Right Power", rearRight.getPower());
        telemetry.addData("Gamepad Y", gamepad1.y);
        telemetry.addData("DpadUp", gamepad1.dpad_up);
        telemetry.addData("DpadDown", gamepad1.dpad_down);
        telemetry.addData("Max Power", MaxPower);
        telemetry.addData("X", X);
        telemetry.addData("Y", Y);
        telemetry.addData("RX", RX);
        telemetry.update();
      }
    }
  }

  /**
   * Describe this function...
   */
  private double fabsolute(double value, double power) {
    double fabs_value;

    if (value > 0) {
      fabs_value = JavaUtil.minOfList(JavaUtil.createListWith(value, power));
    } else if (value < 0) {
      fabs_value = JavaUtil.maxOfList(JavaUtil.createListWith(value, -power));
    } else {
      fabs_value = value;
    }
    return fabs_value;
  }
  /*public void elevatorEncoder(int ticks, double power) {
        int NewLeftLiftTarget;
        int NewRightLiftTarget;


        //creates target position for slides to go to
        NewRightLiftTarget = slideRight.getCurrentPosition() + (int) (ticks);
        NewLeftLiftTarget = slideLeft.getCurrentPosition() + (int) (ticks);

        //sets it
        slideRight.setTargetPosition(NewRightLiftTarget);
        slideLeft.setTargetPosition(NewLeftLiftTarget);

        //runs to new position
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        
        //with this power
        slideRight.setPower(power);
        slideLeft.setPower(power);

        //use this if you want the robot to do something as the slides are moving
        while (slideRight.isBusy() || slideLeft.isBusy()) {
          //for multithreading
        }
        
        //once not busy, set power to 0
        slideRight.setPower(0);
        slideLeft.setPower(0);
        
        
        //change mode back to run w/ encoders
        slideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);*/

  }
