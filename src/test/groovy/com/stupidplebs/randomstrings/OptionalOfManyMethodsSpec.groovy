package com.stupidplebs.randomstrings

import spock.lang.Specification
import spock.util.mop.ConfineMetaClassChanges


@ConfineMetaClassChanges(List)
class OptionalOfManyMethodsSpec extends Specification {
    def setupSpec() {
        List.metaClass.collectWithIndex = { body->
            [delegate, 0..<delegate.size()].transpose().collect(body)
        }
    }

	def "optionalCharactersOf should append no characters when nextBoolean returns false"() {
		given:
		def characters = '!@#$%'
		
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(characters.length())
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalCharactersOf(characters).
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalLetters should append no characters when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalLetters().
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalNumbers should append no characters when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalNumbers().
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalLowercaseLetters should append no characters when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalLowercaseLetters().
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalUppercaseLetters should append no characters when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalUppercaseLetters().
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalWhitespaceCharacters should append no characters when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalWhitespaceCharacters().
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalElementOf should append nothing when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalElementOf(["one", "two", "three"]).
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalKeyOf should append nothing when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalKeyOf(["one" : 1, "two" : 2, "three" : 3]).
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalValueOf should append nothing when nextBoolean returns false"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> false
			0 * nextInt(_ as Integer)
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalValueOf(["one" : 1, "two" : 2, "three" : 3]).
			build()
			
		then:
		actualString == 'Value'

	}
	
	def "optionalCharactersOf should append up to randomUpperLimit when butNoMoreThan is not specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(25) >> 3 // how many letters to append
			3 * nextInt(5) >>> [3, 1, 2] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalCharactersOf('!@#$%').
			build()
			
		then:
		actualString == 'Value$@#'

	}
	
	def "optionalLetters should append up to randomUpperLimit when butNoMoreThan is not specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(25) >> 4 // how many letters to append
			4 * nextInt(52) >>> [0, 25, 26, 51] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalLetters().
			build()
			
		then:
		actualString == 'ValueazAZ'

	}
	
	def "optionalNumbers should append up to randomUpperLimit when butNoMoreThan is not specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(25) >> 3 // how many numbers to append
			3 * nextInt(10) >>> [8, 4, 6] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalNumbers().
			build()
			
		then:
		actualString == 'Value846'

	}
	
	def "optionalLowercaseLetters should append up to randomUpperLimit when butNoMoreThan is not specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(25) >> 4 // how many letters to append
			4 * nextInt(26) >>> [0, 12, 13, 25] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalLowercaseLetters().
			build()
			
		then:
		actualString == 'Valueamnz'

	}
	
	def "optionalUppercaseLetters should append up to randomUpperLimit when butNoMoreThan is not specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(25) >> 4 // how many letters to append
			4 * nextInt(26) >>> [0, 12, 13, 25] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalUppercaseLetters().
			build()
			
		then:
		actualString == 'ValueAMNZ'

	}
	
	def "optionalWhitespaceCharacters should append up to randomUpperLimit when butNoMoreThan is not specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(25) >> 4 // how many whitespace chars to append
			4 * nextInt(2) >>> [0, 1, 1, 0] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalWhitespaceCharacters().
			build()
			
		then:
		actualString == 'Value \t\t '

	}
 
	def "optionalElementOf should append 1 element of the supplied list when nextBoolean return true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(4) >> elementIdx
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			optionalElementOf(["one", "two", "three", "four"]).
			build()
			
		then:
		actualString == expectedOutput

		where:
		[elementIdx, expectedOutput] << ["one", "two", "three", "four"].collectWithIndex { it, idx -> [idx, it] }

	}
	   
	def "optionalKeyOf should append 1 key of the supplied map when nextBoolean return true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(3) >> elementIdx
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			optionalKeyOf(["one": "foo", "two": "bar", "three": "baz"]).
			build()
			
		then:
		actualString == expectedOutput

		where:
		[elementIdx, expectedOutput] << ["one", "two", "three"].collectWithIndex { it, idx -> [idx, it] }

	}
	   
	def "optionalValueOf should append 1 value of the supplied map when nextBoolean return true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(3) >> elementIdx
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			optionalValueOf(["one": "foo", "two": "bar", "three": "baz"]).
			build()
			
		then:
		actualString == expectedOutput

		where:
		[elementIdx, expectedOutput] << ["foo", "bar", "baz"].collectWithIndex { it, idx -> [idx, it] }

	}
	 
	def "optionalCharactersOf should append up to butNoMoreThan when specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(4) >> 3 // how many letters to append
			3 * nextInt(5) >>> [3, 1, 2] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalCharactersOf('!@#$%', 4).
			build()
			
		then:
		actualString == 'Value$@#'

	}
		  
	def "optionalLetters should append up to butNoMoreThan when specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(17) >> 4 // how many letters to append
			4 * nextInt(52) >>> [0, 25, 26, 51] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalLetters(17).
			build()
			
		then:
		actualString == 'ValueazAZ'

	}
	
	def "optionalNumbers should append up to butNoMoreThan when specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(17) >> 3 // how many numbers to append
			3 * nextInt(10) >>> [8, 4, 6] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalNumbers(17).
			build()
			
		then:
		actualString == 'Value846'

	}
	
	def "optionalLowercaseLetters should append up to butNoMoreThan when specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(17) >> 4 // how many letters to append
			4 * nextInt(26) >>> [0, 12, 13, 25] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalLowercaseLetters(17).
			build()
			
		then:
		actualString == 'Valueamnz'

	}
	
	def "optionalUppercaseLetters should append up to butNoMoreThan when specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(17) >> 4 // how many letters to append
			4 * nextInt(26) >>> [0, 12, 13, 25] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalUppercaseLetters(17).
			build()
			
		then:
		actualString == 'ValueAMNZ'

	}
	
	def "optionalWhitespaceCharacters should append up to butNoMoreThan when specified and nextBoolean returns true"() {
		given:
		def randomnessProvider = Mock(RandomnessProvider) {
			1 * nextBoolean() >> true
			1 * nextInt(17) >> 4 // how many whitespace chars to append
			4 * nextInt(2) >>> [0, 1, 1, 0] // the character indexes to append
		}
		
		when:
		def actualString = new RandomStringGenerator.Builder(randomnessProvider).
			is("Value").
			optionalWhitespaceCharacters(17).
			build()
			
		then:
		actualString == 'Value \t\t '

	}


}