/*
 * The Alphanum Algorithm is an improved sorting algorithm for strings
 * containing numbers.  Instead of sorting numbers in ASCII order like
 * a standard sort, this algorithm sorts numbers in numeric order.
 *
 * The Alphanum Algorithm is discussed at http://www.DaveKoelle.com
 *
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301 USA
 *
 */

import java.io.Serializable;
import java.util.Comparator;

/**
 * This is an updated version of the
 * <a href="http://www.davekoelle.com/alphanum.html">Alphanum Algorithm</a>
 * with enhancements made by Daniel Migowski, Andre Bogus, David Koelle,
 * and Florian J. Breunig.
 */
public final class AlphanumComparator
implements Comparator<String>, Serializable {

   /**
    * Alphanum comparator instance.
    */
   public static final Comparator<String> ALPHANUM
   = new AlphanumComparator();

   /**
    * Serialization.
    */
   private static final long serialVersionUID = -6479933364695408622L;

   /**
    * The lowest ASCII symbol representing a digit.
    */
   private static final int  DIGIT_LOWER_BOUND = 48;

   /**
    * The highest ASCII symbol representing a digit.
    */
   private static final int  DIGIT_UPPER_BOUND = 57;

   /**
    * Restrictive constructor.
    */
   private AlphanumComparator() {
      // Prevents this class from being instantiated from the outside.
   }

   /**
    * @param character The character
    * @return <code>true</code> if the given char represents a digit;
    * <code>false</code> otherwise.
    */
   private boolean isDigit(final char character) {
      return character >= DIGIT_LOWER_BOUND
      && character <= DIGIT_UPPER_BOUND;
   }

   /**
    * The length of the string is passed in for improved efficiency
    * (only need to calculate it once).
    * @param string The string to be analyzed.
    * @param slength The length of the string to be analyzed.
    * @param marker The index of the string to start analyzing from.
    * @return A substring containing only digits or only non-digits.
    */
   private String getChunk(
         final String string,
         final int slength,
         final int marker) {
      int index = marker;
      final StringBuilder chunk = new StringBuilder();
      char character = string.charAt(index);
      chunk.append(character);
      index++;
      if (this.isDigit(character)) {
         while (index < slength) {
            character = string.charAt(index);
            if (!this.isDigit(character)) {
               break;
            }
            chunk.append(character);
            index++;
         }
      } else {
         while (index < slength) {
            character = string.charAt(index);
            if (this.isDigit(character)) {
               break;
            }
            chunk.append(character);
            index++;
         }
      }
      return chunk.toString();
   }

   /**
    * @param string1 The first string to be compared.
    * @param string2 The second string to be compared.
    * @return A negative integer, zero, or a positive integer as the first
    * argument is less than, equal to, or greater than the second.
    */
   public int compare(final String string1, final String string2) {
      int thisMarker = 0;
      int thatMarker = 0;
      final int s1Length = string1.length();
      final int s2Length = string2.length();

      while (thisMarker < s1Length && thatMarker < s2Length) {
         final String thisChunk
         = this.getChunk(string1, s1Length, thisMarker);
         thisMarker += thisChunk.length();

         final String thatChunk
         = this.getChunk(string2, s2Length, thatMarker);
         thatMarker += thatChunk.length();

         // If both chunks contain numeric characters, sort them numerically
         int result = 0;
         if (this.isDigit(thisChunk.charAt(0))
               && this.isDigit(thatChunk.charAt(0))) {
            // Simple chunk comparison by length.
            final int thisChunkLength = thisChunk.length();
            result = thisChunkLength - thatChunk.length();
            // If equal, the first different number counts
            if (result == 0) {
               for (int i = 0; i < thisChunkLength; i++) {
                  result = thisChunk.charAt(i) - thatChunk.charAt(i);
                  if (result != 0) {
                     return result;
                  }
               }
            }
         } else {
            result = thisChunk.compareTo(thatChunk);
         }

         if (result != 0) {
            return result;
         }
      }

      return s1Length - s2Length;
   }
}
