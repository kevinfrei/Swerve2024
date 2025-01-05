package org.firstinspires.ftc.teamcode.drive.opmode;


import static java.lang.Math.toRadians;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.drive.SampleSwerveDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.util.ConfigurablePose;

import java.util.ArrayList;
import java.util.List;

/*
 * This is an example of a more complex path to really test the tuning.
 */
@Config
@Autonomous(group = "drive")
public class ColinAuto extends LinearOpMode {
    public static ConfigurablePose START = new ConfigurablePose(0,0, toRadians(90));
    public static ConfigurablePose END = new ConfigurablePose(-12, 0, toRadians(90));


    @Override
    public void runOpMode() throws InterruptedException {
        SampleSwerveDrive drive = new SampleSwerveDrive(hardwareMap);
        drive.setPoseEstimate(START.toPose());
        drive.setExternalHeading(START.getHeading());

       TrajectorySequence sequence = SampleSwerveDrive.trajectorySequenceBuilder(START.toPose())
                .lineToLinearHeading(END.toPose())
                .build();

        waitForStart();


        drive.startIMUThread(this);
        if (isStopRequested()) return;

        while (opModeIsActive() && !isStopRequested()){
            if(!drive.isBusy()){
                drive.followTrajectorySequenceAsync(sequence);
            }
            drive.update();

        }


    }
}
