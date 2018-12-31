package design;

import junit.framework.TestCase;

import java.util.Arrays;
import java.util.Optional;
import java.util.OptionalInt;

/** Given a gas station with 3 dispensers with amount X,Y,Z and cars queue A, calculate waiting time for all of them to refill */
// NOTE: this solution didn't achieve 100% on Codility
public class CarsRefill extends TestCase {
    public int solution(int[] A, int X, int Y, int Z) {
        FuelStation station = new FuelStation(X, Y, Z);
        int time = 0;
        int carIndex = 0;

        while (true) {
            while (carIndex < A.length) {
                int requestedAmount = A[carIndex];
                Optional<Dispenser> dispenser = station.tryRefill(requestedAmount);
                if (dispenser.isPresent()) {
                    dispenser.get().occupy(requestedAmount);
                    carIndex++;
                } else {
                    break;
                }
            }

            if (carIndex == A.length) {
                break;
            }

            OptionalInt timeUpdate = station.getNextTimeUpdate();
            if (timeUpdate.isPresent() && timeUpdate.getAsInt() > 0) {
                int value = timeUpdate.getAsInt();
                time += value;
                station.wait(value);
            } else {
                return -1; // all stations are free, but none has right amount
            }
        }

        return time;
    }

    private class FuelStation {
        Dispenser[] dispensers;

        FuelStation(int X, int Y, int Z) {
            dispensers = new Dispenser[] {
                new Dispenser(X),
                new Dispenser(Y),
                new Dispenser(Z)
            };
        }

        void wait(int time) {
            Arrays.stream(dispensers)
                .filter(Dispenser::isOccupied)
                .forEach(d -> {
                    d.amount -= time;
                    d.occupiedTimeLeft -= time;
                });
        }

        OptionalInt getNextTimeUpdate() {
            return Arrays.stream(dispensers)
                .filter(Dispenser::isOccupied)
                .mapToInt(d -> d.occupiedTimeLeft)
                .min();
        }

        Optional<Dispenser> tryRefill(int requestedAmount) {
            return Arrays.stream(dispensers)
                .filter(d -> d.isFree() && d.amount >= requestedAmount)
                .findFirst();
        }

        @Override
        public String toString() {
            return String.format("FuelStation[%s, %s, %s]", dispensers[0], dispensers[1], dispensers[2]);
        }
    }

    private class Dispenser {
        int amount;
        int occupiedTimeLeft;

        Dispenser(int amount) {
            this.amount = amount;
            this.occupiedTimeLeft = 0;
        }

        void occupy(int requestedAmount) {
            occupiedTimeLeft = requestedAmount;
        }

        boolean isFree() {
            return occupiedTimeLeft == 0;
        }

        boolean isOccupied() {
            return !isFree();
        }

        @Override
        public String toString() {
            if (isFree()) {
                return String.format("Free[%s]", amount);
            } else {
                return String.format("Occupied[%s / %s]", amount, occupiedTimeLeft);
            }
        }
    }

    public void test() {
        assertEquals(8, solution(new int[] {2, 8, 4, 3, 2}, 7, 11, 3));
        assertEquals(0, solution(new int[] {1}, 3, 2, 1));
        assertEquals(-1, solution(new int[] {10}, 3, 2, 1));
        assertEquals(-1, solution(new int[] {1, 2, 3}, 3, 2, 1));
        assertEquals(2, solution(new int[] {1, 2, 3}, 3, 5, 1));
    }
}
