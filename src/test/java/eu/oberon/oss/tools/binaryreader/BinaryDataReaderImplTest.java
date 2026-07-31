package eu.oberon.oss.tools.binaryreader;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryDataReaderImplTest {

    @Test
    void remainingReturnsTotalSizeBeforeReading() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        int remaining = reader.remaining();

        assertEquals(4, remaining);
    }

    @Test
    void remainingReturnsUnreadByteCountAfterReadingBytes() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(2);

        assertEquals(2, reader.remaining());
    }

    @Test
    void remainingReturnsZeroWhenAllBytesHaveBeenRead() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(4);

        assertEquals(0, reader.remaining());
    }

    @Test
    void hasRemainingReturnsTrueBeforeAllBytesHaveBeenRead() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(3);

        assertTrue(reader.hasRemaining());
    }

    @Test
    void hasRemainingReturnsFalseWhenAllBytesHaveBeenRead() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(4);

        assertFalse(reader.hasRemaining());
    }

    @Test
    void hasRemainingReturnsFalseForEmptyReader() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[0]).getReader();

        assertFalse(reader.hasRemaining());
        assertEquals(0, reader.remaining());
    }

    @Test
    void hasRemainingLengthReturnsTrueWhenEnoughBytesRemain() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(1);

        assertTrue(reader.hasRemaining(3));
    }

    @Test
    void hasRemainingLengthReturnsTrueWhenRequestedLengthExactlyMatchesRemainingBytes() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(2);

        assertTrue(reader.hasRemaining(2));
    }

    @Test
    void hasRemainingLengthReturnsFalseWhenNotEnoughBytesRemain() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(2);

        assertFalse(reader.hasRemaining(3));
    }

    @Test
    void hasRemainingLengthReturnsTrueForZeroLength() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(4);

        assertTrue(reader.hasRemaining(0));
    }

    @Test
    void hasRemainingLengthThrowsIllegalArgumentExceptionForNegativeLength() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> reader.hasRemaining(-1)
        );

        assertEquals("length must not be negative", exception.getMessage());
    }

    @Test
    void matchesReturnsTrueWhenBytesAtCurrentOffsetMatchExpectedPattern() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertTrue(reader.matches(new byte[]{1, 2}));
    }

    @Test
    void matchesReturnsFalseWhenBytesAtCurrentOffsetDoNotMatchExpectedPattern() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertFalse(reader.matches(new byte[]{1, 3}));
    }

    @Test
    void matchesUsesCurrentReaderOffset() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readByte();

        assertTrue(reader.matches(new byte[]{2, 3}));
    }

    @Test
    void matchesDoesNotAdvanceReaderOffset() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertTrue(reader.matches(new byte[]{1, 2}));
        assertEquals(4, reader.remaining());
        assertEquals(1, reader.readByte());
    }

    @Test
    void matchesReturnsFalseWhenExpectedPatternIsLongerThanRemainingBytes() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        reader.readBytes(3);

        assertFalse(reader.matches(new byte[]{4, 5}));
        assertEquals(1, reader.remaining());
    }

    @Test
    void matchesReturnsTrueForEmptyExpectedPatternAtCurrentOffset() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertTrue(reader.matches(new byte[0]));
        assertEquals(4, reader.remaining());
    }

    @Test
    void matchesThrowsNullPointerExceptionWhenExpectedPatternIsNull() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> reader.matches(null)
        );

        assertEquals("Parameter: expected", exception.getMessage());
    }

    @Test
    void matchesOffsetReturnsTrueWhenBytesAtOffsetMatchExpectedPattern() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertTrue(reader.matches(1, new byte[]{2, 3}));
    }

    @Test
    void matchesOffsetReturnsFalseWhenBytesAtOffsetDoNotMatchExpectedPattern() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertFalse(reader.matches(1, new byte[]{2, 4}));
    }

    @Test
    void matchesOffsetDoesNotAdvanceReaderOffset() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertTrue(reader.matches(2, new byte[]{3, 4}));
        assertEquals(4, reader.remaining());
        assertEquals(1, reader.readByte());
    }

    @Test
    void matchesOffsetReturnsFalseWhenExpectedPatternExtendsPastEndOfData() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertFalse(reader.matches(3, new byte[]{4, 5}));
        assertEquals(4, reader.remaining());
    }

    @Test
    void matchesOffsetReturnsTrueForEmptyExpectedPatternAtEndOffset() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertTrue(reader.matches(4, new byte[0]));
        assertEquals(4, reader.remaining());
    }

    @Test
    void matchesOffsetThrowsNullPointerExceptionWhenExpectedPatternIsNull() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        NullPointerException exception = assertThrows(
                NullPointerException.class,
                () -> reader.matches(0, null)
        );

        assertEquals("Parameter: expected", exception.getMessage());
    }

    @Test
    void matchesOffsetThrowsIndexOutOfBoundsExceptionWhenOffsetIsNegative() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> reader.matches(-1, new byte[]{1})
        );
    }

    @Test
    void matchesOffsetThrowsIndexOutOfBoundsExceptionWhenOffsetIsGreaterThanSize() {
        BinaryDataReader reader = new BinaryDataViewerImpl(new byte[]{1, 2, 3, 4}).getReader();

        assertThrows(
                IndexOutOfBoundsException.class,
                () -> reader.matches(5, new byte[0])
        );
    }

    @Test
    void testSkipForward() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataReader reader = new BinaryDataReaderImpl(new BinaryDataViewerImpl(data));

        reader.skip(3);

        assertEquals(3, reader.offset());
        assertEquals(2, reader.remaining());
    }

    @Test
    void testSkipBackward() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataReader reader = new BinaryDataReaderImpl(new BinaryDataViewerImpl(data));

        reader.skip(4);
        reader.skip(-2);

        assertEquals(2, reader.offset());
        assertEquals(3, reader.remaining());
    }

    @Test
    void testSkipZeroDoesNotChangeOffset() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataReader reader = new BinaryDataReaderImpl(new BinaryDataViewerImpl(data));

        reader.skip(2);
        reader.skip(0);

        assertEquals(2, reader.offset());
        assertEquals(3, reader.remaining());
    }

    @Test
    void testSkipToEnd() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataReader reader = new BinaryDataReaderImpl(new BinaryDataViewerImpl(data));

        reader.skip(data.length);

        assertEquals(data.length, reader.offset());
        assertEquals(0, reader.remaining());
    }

    @Test
    void testSkipBeyondEndThrowsExceptionAndKeepsOffset() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataReader reader = new BinaryDataReaderImpl(new BinaryDataViewerImpl(data));

        reader.skip(2);

        assertThrows(IndexOutOfBoundsException.class, () -> reader.skip(4));
        assertEquals(2, reader.offset());
    }

    @Test
    void testSkipBeforeStartThrowsExceptionAndKeepsOffset() {
        byte[] data = {1, 2, 3, 4, 5};
        BinaryDataReader reader = new BinaryDataReaderImpl(new BinaryDataViewerImpl(data));

        reader.skip(2);

        assertThrows(IndexOutOfBoundsException.class, () -> reader.skip(-3));
        assertEquals(2, reader.offset());
    }

    @Nested
    @DisplayName("Construction")
    class Construction {

        @Test
        @DisplayName("rejects null viewer")
        void rejectsNullViewer() {
            assertThrows(NullPointerException.class, () -> new BinaryDataReaderImpl(null));
        }

        @Test
        @DisplayName("starts at offset zero")
        void startsAtOffsetZero() {
            BinaryDataReaderImpl reader = new BinaryDataReaderImpl(new BinaryDataViewerImpl(new byte[]{0x01}));

            assertEquals(0, reader.offset());
        }
    }

    @Nested
    @DisplayName("offset")
    class Offset {

        private final BinaryDataReaderImpl reader = new BinaryDataReaderImpl(
                new BinaryDataViewerImpl(new byte[]{0x01, 0x02, 0x03})
        );

        @Test
        @DisplayName("sets offset to valid position")
        void setsOffsetToValidPosition() {
            reader.offset(2);

            assertEquals(2, reader.offset());
        }

        @Test
        @DisplayName("sets offset to end position")
        void setsOffsetToEndPosition() {
            reader.offset(3);

            assertEquals(3, reader.offset());
        }

        @Test
        @DisplayName("rejects negative offset")
        void rejectsNegativeOffset() {
            assertThrows(IndexOutOfBoundsException.class, () -> reader.offset(-1));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("rejects offset greater than size")
        void rejectsOffsetGreaterThanSize() {
            assertThrows(IndexOutOfBoundsException.class, () -> reader.offset(4));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("does not change current offset when rejected")
        void doesNotChangeCurrentOffsetWhenRejected() {
            reader.offset(1);

            assertThrows(IndexOutOfBoundsException.class, () -> reader.offset(4));

            assertEquals(1, reader.offset());
        }
    }

    @Nested
    @DisplayName("readByte()")
    class ReadByteAtCurrentOffset {

        private final BinaryDataReaderImpl reader = new BinaryDataReaderImpl(
                new BinaryDataViewerImpl(new byte[]{0x01, 0x02, 0x03})
        );

        @Test
        @DisplayName("reads byte at current offset")
        void readsByteAtCurrentOffset() {
            assertEquals(0x01, reader.readByte());
        }

        @Test
        @DisplayName("advances offset by one")
        void advancesOffsetByOne() {
            reader.readByte();

            assertEquals(1, reader.offset());
        }

        @Test
        @DisplayName("reads sequential bytes")
        void readsSequentialBytes() {
            assertAll(
                    () -> assertEquals(0x01, reader.readByte()),
                    () -> assertEquals(0x02, reader.readByte()),
                    () -> assertEquals(0x03, reader.readByte()),
                    () -> assertEquals(3, reader.offset())
            );
        }

        @Test
        @DisplayName("rejects read at end offset")
        void rejectsReadAtEndOffset() {
            reader.offset(3);

            assertThrows(IndexOutOfBoundsException.class, reader::readByte);
            assertEquals(3, reader.offset());
        }
    }

    @Nested
    @DisplayName("readByte(offset)")
    class ReadByteAtAbsoluteOffset {

        private final BinaryDataReaderImpl reader = new BinaryDataReaderImpl(
                new BinaryDataViewerImpl(new byte[]{0x01, 0x02, 0x03})
        );

        @Test
        @DisplayName("reads byte at absolute offset")
        void readsByteAtAbsoluteOffset() {
            assertEquals(0x02, reader.readByte(1));
        }

        @Test
        @DisplayName("sets offset to next byte after absolute read")
        void setsOffsetToNextByteAfterAbsoluteRead() {
            reader.readByte(1);

            assertEquals(2, reader.offset());
        }

        @Test
        @DisplayName("absolute read does not depend on current offset")
        void absoluteReadDoesNotDependOnCurrentOffset() {
            reader.offset(2);

            byte value = reader.readByte(0);

            assertAll(
                    () -> assertEquals(0x01, value),
                    () -> assertEquals(1, reader.offset())
            );
        }

        @Test
        @DisplayName("rejects negative offset")
        void rejectsNegativeOffset() {
            assertThrows(IndexOutOfBoundsException.class, () -> reader.readByte(-1));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("rejects offset equal to size")
        void rejectsOffsetEqualToSize() {
            assertThrows(IndexOutOfBoundsException.class, () -> reader.readByte(3));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("does not change current offset when rejected")
        void doesNotChangeCurrentOffsetWhenRejected() {
            reader.offset(1);

            assertThrows(IndexOutOfBoundsException.class, () -> reader.readByte(3));

            assertEquals(1, reader.offset());
        }
    }

    @Nested
    @DisplayName("readBytes(length)")
    class ReadBytesAtCurrentOffset {

        private final BinaryDataReaderImpl reader = new BinaryDataReaderImpl(
                new BinaryDataViewerImpl(new byte[]{0x01, 0x02, 0x03, 0x04})
        );

        @Test
        @DisplayName("reads requested bytes at current offset")
        void readsRequestedBytesAtCurrentOffset() {
            reader.offset(1);

            byte[] bytes = reader.readBytes(2);

            assertArrayEquals(new byte[]{0x02, 0x03}, bytes);
        }

        @Test
        @DisplayName("advances offset by requested length")
        void advancesOffsetByRequestedLength() {
            reader.readBytes(3);

            assertEquals(3, reader.offset());
        }

        @Test
        @DisplayName("supports zero length")
        void supportsZeroLength() {
            reader.offset(2);

            byte[] bytes = reader.readBytes(0);

            assertAll(
                    () -> assertArrayEquals(new byte[]{}, bytes),
                    () -> assertEquals(2, reader.offset())
            );
        }

        @Test
        @DisplayName("returns a defensive copy")
        void returnsDefensiveCopy() {
            byte[] bytes = reader.readBytes(2);
            bytes[0] = 0x7F;

            reader.offset(0);

            assertArrayEquals(new byte[]{0x01, 0x02}, reader.readBytes(2));
        }

        @Test
        @DisplayName("rejects negative length")
        void rejectsNegativeLength() {
            assertThrows(IllegalArgumentException.class, () -> reader.readBytes(-1));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("rejects range that exceeds size")
        void rejectsRangeThatExceedsSize() {
            reader.offset(3);

            assertThrows(IndexOutOfBoundsException.class, () -> reader.readBytes(2));
            assertEquals(3, reader.offset());
        }
    }

    @Nested
    @DisplayName("readBytes(offset, length)")
    class ReadBytesAtAbsoluteOffset {

        private final BinaryDataReaderImpl reader = new BinaryDataReaderImpl(
                new BinaryDataViewerImpl(new byte[]{0x01, 0x02, 0x03, 0x04})
        );

        @Test
        @DisplayName("reads requested bytes at absolute offset")
        void readsRequestedBytesAtAbsoluteOffset() {
            byte[] bytes = reader.readBytes(1, 2);

            assertArrayEquals(new byte[]{0x02, 0x03}, bytes);
        }

        @Test
        @DisplayName("sets offset to end of absolute read range")
        void setsOffsetToEndOfAbsoluteReadRange() {
            reader.readBytes(1, 2);

            assertEquals(3, reader.offset());
        }

        @Test
        @DisplayName("absolute read does not depend on current offset")
        void absoluteReadDoesNotDependOnCurrentOffset() {
            reader.offset(3);

            byte[] bytes = reader.readBytes(0, 2);

            assertAll(
                    () -> assertArrayEquals(new byte[]{0x01, 0x02}, bytes),
                    () -> assertEquals(2, reader.offset())
            );
        }

        @Test
        @DisplayName("supports zero length at end")
        void supportsZeroLengthAtEnd() {
            byte[] bytes = reader.readBytes(4, 0);

            assertAll(
                    () -> assertArrayEquals(new byte[]{}, bytes),
                    () -> assertEquals(4, reader.offset())
            );
        }

        @Test
        @DisplayName("rejects negative length")
        void rejectsNegativeLength() {
            assertThrows(IllegalArgumentException.class, () -> reader.readBytes(0, -1));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("rejects negative offset")
        void rejectsNegativeOffset() {
            assertThrows(IndexOutOfBoundsException.class, () -> reader.readBytes(-1, 1));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("rejects range that exceeds size")
        void rejectsRangeThatExceedsSize() {
            reader.offset(1);

            assertThrows(IndexOutOfBoundsException.class, () -> reader.readBytes(3, 2));

            assertEquals(1, reader.offset());
        }
    }

    @Nested
    @DisplayName("readBytes(target, offset)")
    class ReadBytesIntoTarget {

        private final BinaryDataReaderImpl reader = new BinaryDataReaderImpl(
                new BinaryDataViewerImpl(new byte[]{0x01, 0x02, 0x03, 0x04})
        );

        @Test
        @DisplayName("copies requested bytes into target")
        void copiesRequestedBytesIntoTarget() {
            byte[] target = new byte[2];

            reader.readBytes(target, 1);

            assertArrayEquals(new byte[]{0x02, 0x03}, target);
        }

        @Test
        @DisplayName("sets offset to end of copied range")
        void setsOffsetToEndOfCopiedRange() {
            byte[] target = new byte[2];

            reader.readBytes(target, 1);

            assertEquals(3, reader.offset());
        }

        @Test
        @DisplayName("supports empty target at end")
        void supportsEmptyTargetAtEnd() {
            byte[] target = new byte[]{};

            reader.readBytes(target, 4);

            assertAll(
                    () -> assertArrayEquals(new byte[]{}, target),
                    () -> assertEquals(4, reader.offset())
            );
        }

        @Test
        @DisplayName("rejects null target")
        void rejectsNullTarget() {
            assertThrows(NullPointerException.class, () -> reader.readBytes(null, 0));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("rejects negative offset")
        void rejectsNegativeOffset() {
            byte[] target = new byte[1];

            assertThrows(IndexOutOfBoundsException.class, () -> reader.readBytes(target, -1));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("rejects range that exceeds size")
        void rejectsRangeThatExceedsSize() {
            byte[] target = new byte[2];

            assertThrows(IndexOutOfBoundsException.class, () -> reader.readBytes(target, 3));
            assertEquals(0, reader.offset());
        }

        @Test
        @DisplayName("leaves target and offset unchanged when range is invalid")
        void leavesTargetAndOffsetUnchangedWhenRangeIsInvalid() {
            byte[] target = {0x55, 0x66};
            reader.offset(1);

            assertThrows(IndexOutOfBoundsException.class, () -> reader.readBytes(target, 3));

            assertAll(
                    () -> assertArrayEquals(new byte[]{0x55, 0x66}, target),
                    () -> assertEquals(1, reader.offset())
            );
        }
    }

    @Nested
    @DisplayName("getViewer()")
    class GetViewer {

        @Test
        @DisplayName("returns the underlying viewer")
        void returnsUnderlyingViewer() {
            BinaryDataViewer viewer = new BinaryDataViewerImpl(new byte[]{1, 2, 3});
            BinaryDataReader reader = new BinaryDataReaderImpl(viewer);

            assertSame(viewer, reader.getViewer());
        }
    }
}