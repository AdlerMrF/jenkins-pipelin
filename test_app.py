import unittest
from app import soma, subtrai, multiplica


class TestCalculadora(unittest.TestCase):
    def test_soma(self):
        self.assertEqual(soma(2, 3), 5)

    def test_subtrai(self):
        self.assertEqual(subtrai(5, 3), 2)

    def test_multiplica(self):
        self.assertEqual(multiplica(4, 3), 12)


if __name__ == "__main__":
    unittest.main()
