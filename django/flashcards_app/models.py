from django.db import models
from django.contrib.auth.models import User

class FlashcardList(models.Model):
    
    # Timestamp at the moment that this FlashcardList was created.
    created_timestamp = models.DateTimeField(auto_now_add=True)

    # Timestamp at the moment that this FlashcardList was last modified.
    modified_timestamp = models.DateTimeField(auto_now=True)

    # Points to the User that created this FlashcardList.
    created_by_user = models.ForeignKey(User, related_name='flashcard_lists')
    
    # Name of the FlashcardList
    name = models.CharField(max_length=128)
    
    # Description of the FlashcardList.
    description = models.CharField(max_length=4096, blank=True)

    
class Flashcard(models.Model):

    # Timestamp at the moment that this Flashcard was created.
    created_timestamp = models.DateTimeField(auto_now_add=True)

    # Timestamp at the moment that this Flashcard was last modified.
    modified_timestamp = models.DateTimeField(auto_now=True)

    # Points to the User that created this Flashcard.
    created_by_user = models.ForeignKey(User, related_name='flashcards')
    
    # Points to the FlashcardList that has this Flashcard.
    flashcard_list = models.ForeignKey(FlashcardList, related_name='flashcards')
    
    # Text on side 1 of the flashcard.
    text_side_1 = models.CharField(max_length=8192, blank=True)
    
    # Text on side 2 of the flashcard.
    text_side_2 = models.CharField(max_length=8192, blank=True)

    # User notes on this flashcard.
    user_notes = models.CharField(max_length=8192, blank=True)

