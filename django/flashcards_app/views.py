from django.contrib.auth.models import User, Group
from rest_framework import viewsets

from rest_framework.permissions import IsAuthenticated
from rest_framework.permissions import IsAdminUser
from rest_framework.permissions import IsAuthenticatedOrReadOnly

from .serializers import UserSerializer
from .serializers import GroupSerializer
from .serializers import FlashcardListSerializer
from .serializers import FlashcardSerializer
from .models import FlashcardList
from .models import Flashcard
from .permissions import IsCreatorOrReadOnly
from .permissions import IsCreator

class UserViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows users to be viewed or edited.
    """
    serializer_class = UserSerializer
    permission_classes = (IsAuthenticated,)

    def get_queryset(self):
        """
        If the user is a superuser, then the view should return all the users.
        Otherwise, the view should return only the current authenticated user.
        """
        if self.request.user.is_superuser:
            return User.objects.all().order_by('-date_joined')
        else:
            user = self.request.user
            userIds = [user.pk]
            return User.objects.filter(id__in=userIds)

class GroupViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """
    serializer_class = GroupSerializer
    permission_classes = (IsAuthenticated,)

    def get_queryset(self):
        """
        If the user is a superuser, then the view should return all the groups.
        Otherwise, the view should return only the current authenticated
        user's groups.
        """
        if self.request.user.is_superuser:
            return Group.objects.all()
        else:
            user = self.request.user
            groupIds = []
            for group in user.groups.all():
                groupIds.add(group.id)
            return Group.objects.filter(id__in=groupIds)

class FlashcardListViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows FlashcardLists to be viewed or edited.
    """
    serializer_class = FlashcardListSerializer
    permission_classes = (IsAuthenticated,)

    def perform_create(self, serializer):
        serializer.save(created_by_user=self.request.user)

    def get_queryset(self):
        """
        If the user is a superuser, then the view should return all the
        flashcard lists.
        Otherwise, the view should return only the current authenticated
        user's flashcard lists.
        """
        if self.request.user.is_superuser:
            return FlashcardList.objects.all().order_by('-modified_timestamp')
        else:
            user = self.request.user
            return FlashcardList.objects.filter(created_by_user=user)

class FlashcardViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows Flashcards to be viewed or edited.
    """
    serializer_class = FlashcardSerializer
    permission_classes = (IsAuthenticated,)

    def perform_create(self, serializer):
        serializer.save(created_by_user=self.request.user)

    def get_queryset(self):
        """
        If the user is a superuser, then the view should return all the
        flashcards.
        Otherwise, the view should return only the current authenticated
        user's flashcards.
        """
        if self.request.user.is_superuser:
            return Flashcard.objects.all().order_by('-modified_timestamp')
        else:
            user = self.request.user
            return Flashcard.objects.filter(created_by_user=user)

