from django.contrib.auth.models import User, Group
from rest_framework import serializers

from .models import FlashcardList
from .models import Flashcard


class UserSerializer(serializers.ModelSerializer):
    flashcardlists = serializers.PrimaryKeyRelatedField(many=True,
                                                queryset=FlashcardList.objects.all())

    class Meta:
        model = User
        fields = ('id',
                  'username',
                  'email',
                  'groups',
                  'flashcardlists',
                  )
        read_only_fields = ('id',
                            'username',
                            'groups',
                            )

class GroupSerializer(serializers.ModelSerializer):
    users = serializers.PrimaryKeyRelatedField(many=True,
                                               queryset=User.objects.all())

    class Meta:
        model = Group
        fields = ('id',
                  'name',
                  'users',
                  )
        read_only_fields = ('id',
                            'name',
                            'users',
                            )

class FlashcardListSerializer(serializers.ModelSerializer):
    flashcards = serializers.PrimaryKeyRelatedField(many=True,
                                                queryset=Flashcard.objects.all())
    created_by_user = serializers.ReadOnlyField(source='created_by_user.id')

    class Meta:
        model = FlashcardList
        fields = ('id',
                  'created_timestamp',
                  'modified_timestamp',
                  'created_by_user',
                  'name',
                  'description',
                  'flashcards',
                  )
        read_only_fields = ('id',
                            'created_timestamp',
                            'modified_timestamp',
                            'created_by_user',
                            )

    def validate(self, data):
        # Make sure the name of the flashcard list is not empty.
        if len(data['name']):
            errMsg = "name must not be empty"
            raise serializers.ValidationError(errMsg)

        return data

class FlashcardSerializer(serializers.ModelSerializer):
    created_by_user = serializers.ReadOnlyField(source='created_by_user.id')

    class Meta:
        model = Flashcard
        fields = ('id',
                  'created_timestamp',
                  'modified_timestamp',
                  'created_by_user',
                  'flashcard_list',
                  'text_side_1',
                  'text_side_2',
                  'user_notes',
                  )
        read_only_fields = ('id',
                            'created_timestamp',
                            'modified_timestamp',
                            'created_by_user',
                            'flashcard_list',
                            )
