from django.contrib.auth.models import User, Group
from rest_framework import viewsets

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
    queryset = User.objects.all().order_by('-date_joined')
    serializer_class = UserSerializer

    permission_classes = (IsCreator,
                          IsAdminUser)


class GroupViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows groups to be viewed or edited.
    """
    queryset = Group.objects.all()
    serializer_class = GroupSerializer

    permission_classes = (IsAuthenticatedOrReadOnly,
                          IsAdminUser)

class FlashcardListViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows FlashcardLists to be viewed or edited.
    """
    queryset = FlashcardList.objects.all().order_by('-modified_timestamp')
    serializer_class = FlashcardListSerializer

    permission_classes = (IsCreatorOrReadOnly,
                          IsAdminUser)

    def perform_create(self, serializer):
        serializer.save(created_by_user=self.request.user)
        
class FlashcardViewSet(viewsets.ModelViewSet):
    """
    API endpoint that allows Flashcards to be viewed or edited.
    """
    queryset = Flashcard.objects.all().order_by('-modified_timestamp')
    serializer_class = FlashcardSerializer

    permission_classes = (IsCreatorOrReadOnly,
                          IsAdminUser)

    def perform_create(self, serializer):
        serializer.save(created_by_user=self.request.user)
