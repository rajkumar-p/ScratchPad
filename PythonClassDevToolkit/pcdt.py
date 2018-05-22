''' Circuitous, LLC -
    An Advanced Circle Analytics Company

'''

import math
from random import random, seed

class Circle(object):
  'An Advanced Circle Analytics Company'

  version = '0.1'

  # Not a constructor but an initializer as the instance
  # has already been constructed
  def __init__(self, radius):
    self.radius = radius

  def area(self):
    'Perform quadrature on a shape of uniform radius'
    return math.pi * self.radius ** 2.0

  def perimeter(self):
    return 2.0 * math.pi * self.radius

class Tire(Circle):
  'Tires are circles with a corrected parameter'

  def perimeter(self):
    'Circumference corrected for the rubber'
    return Circle.perimeter(self) * 1.25

if __name__ == "__main__":
  'Need to seed to reproduce results'
  seed(8675309)
  print 'Circuitous version', Circle.version
  n = 10
  circles = [Circle(random()) for i in xrange(n)]
  print 'The average area of', n, 'random circles'
  avg = sum([c.area() for c in circles]) / n
  print avg

  cuts = [0.1, 0.7, 0.8]
  circles = [Circle(r) for r in cuts]
  for c in circles:
    print 'A circlet with a radius of', c.radius
    print 'has a perimeter of', c.perimeter()
    print 'and a cold area of', c.area()
    c.radius *= 1.1
    print 'and a warm area of', c.area()
    print
