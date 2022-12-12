# aoc

I started in Java because I'm an idiot and it's too late to turn back now.

All of this code is cranked out as quickly as possible for those sweet leaderboard points. Don't judge me.

Tried to be a cool kid on day 2 and do the mod thing, but gave up and went with 2d aRrAyLiStS because I couldn't figure it out fast enough.

Lay eyes upon day 5 and weep at my suffering.

~Day 7 has a memory leak. Probably use shared_ptr / weak_ptr and save myself the trouble rooting it out.~  
Or just don't allocate the memory myself in the first place

Learned something cool on Day 9: I used a Coordinate class and kept a HashSet of Coordinate to keep track of unique locations the tail visited. To keep the HashSet, I had to @override hashCode(). I used some boilerplate hash function, wrote the little method for updating the tail and thought I'd be done super fast. Test input worked, larger input file gave me the wrong answer. Spent way too long trying to come up with some kind of edge case I was missing until I figured out that the problem was that I was getting collisions in the HashSet because of my crusty-ass hashCode(). I threw in a few more operations based on another hashCode() implementation I found online and it worked.
