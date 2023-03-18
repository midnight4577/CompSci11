import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.io.IOException;

public class fileIOtest {

    BallisticCalculator calc;
    Trajectory testInputTraj;
    Projectile testInputProj;

    @Before
    public void setup(){
        //create a trajectory and projectile to test
        testInputProj = new Projectile("test", 0.5, 0.05, 1.204, 1);
        calc = new BallisticCalculator(testInputProj, 100, 45, 0, 0.01, -9.81);
        testInputTraj = calc.calculate();
    }

    //test file reading and writing
    @Test
    public void testWriteRead() throws IOException {
        String data;
        data = "testing asewrthbthsr5b DATA";
        fileIO.writeFile("test.txt", data);
        assertEquals(fileIO.readFile("test.txt"), data);
    }

    //test trajectory encoding and decoding
    @Test
    public void testTrajReadWrite() throws IOException {
        fileIO.writeFile("trajTest.traj", fileIO.encodeTraj(testInputTraj));
        Trajectory testOutputTraj;
        testOutputTraj = fileIO.decodeTraj(fileIO.readFile("trajTest.traj"));
        assertEquals(fileIO.encodeTraj(testInputTraj), fileIO.encodeTraj(testOutputTraj));
    }

    //test projectile encoding and decoding
    @Test
    public void testProjReadWrite() throws IOException {
        fileIO.writeFile("projTest.proj", fileIO.encodeProj(testInputProj));
        Projectile testOutputProj;
        testOutputProj = fileIO.decodeProj(fileIO.readFile("projTest.proj"));
        assertEquals(fileIO.encodeProj(testInputProj), fileIO.encodeProj(testOutputProj));
    }

    //test wrong filename for a trajectory decode
    @Test
    public void testTrajReadFakeFile() throws IOException {
        Trajectory testOutputTraj;
        testOutputTraj = fileIO.decodeTraj(fileIO.readFile("doesNotExist.traj"));
        assertNull(testOutputTraj);
    }

    //test wrong filename for a projectile decode
    @Test
    public void testProjReadFakeFile() throws IOException {
        Projectile testProj;
        testProj = fileIO.decodeProj(fileIO.readFile("doesNotExist.traj"));
        assertNull(testProj);
    }
}
